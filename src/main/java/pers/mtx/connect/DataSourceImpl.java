package pers.mtx.connect;

import pers.mtx.util.YmlUtil;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 连接池具体实现类
 */
public class DataSourceImpl implements DataSource {

    private static final DataSourceImpl dataSource = new DataSourceImpl();
    private final ReentrantLock lock = new ReentrantLock();



    //定义连接池中连接对象的存储容器
    private final List<PoolConnection> list = Collections.synchronizedList(new ArrayList<>());


    //定义数据库连接属性
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    //定义默认连接池属性配置
    private static final int initSize;
    private static final int maxSize;
    private static final int stepSize;
    private static final int timeout;

    static {
        URL = YmlUtil.getSetting().getData().getUrl();
        USERNAME = YmlUtil.getSetting().getData().getUsername();
        PASSWORD = YmlUtil.getSetting().getData().getPassword();
        initSize = YmlUtil.getSetting().getData().getInitSize();
        maxSize = YmlUtil.getSetting().getData().getMaxSize();
        stepSize = YmlUtil.getSetting().getData().getStepSize();
        timeout = YmlUtil.getSetting().getData().getTimeout();
    }

    public DataSourceImpl() {
        initPool();
    }

    public static PoolConnection getConnection(){
        return dataSource.getDataSource();
    }

    //初始化连接池
    private void initPool() {
        try {
            //加载驱动
            Driver driver = (Driver) Class.forName(DRIVER_CLASS).newInstance();
            //使用DriverManager注册驱动
            DriverManager.registerDriver(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public PoolConnection getDataSource() {
        PoolConnection poolConnection = null;
        try {
            lock.lock();
            //连接池对象为空时，初始化连接对象
            if (list.size() == 0) {
                createConnection(initSize);
            }
            //获取可用连接对象
            poolConnection = getAvailableConnection();

            //没有可用连接对象时，等待连接对象的释放或者创建新的连接对象使用
            while (poolConnection == null) {
                //System.out.println("---------------等待连接---------------");
                createConnection(stepSize);
                poolConnection = getAvailableConnection();

                if (poolConnection == null) {
                    TimeUnit.MILLISECONDS.sleep(30);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return poolConnection;
    }


    //创建数据库连接
    private void createConnection(int count) throws SQLException {
        if (list.size() + count <= maxSize) {
            for (int i = 0; i < count; i++) {
                Connection connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PoolConnection pool = new PoolConnection(connect, true);
                list.add(pool);
            }
        }
    }

    //获取可用连接对象
    private PoolConnection getAvailableConnection() throws SQLException {
        for (PoolConnection pool : list) {
            if (pool.isStatus()) {
                Connection con = pool.getConnect();
                //验证连接是否超时
                if (!con.isValid(timeout)) {
                    Connection connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                    pool.setConnect(connect);
                }
                pool.setStatus(false);
                return pool;
            }
        }

        return null;
    }

}
