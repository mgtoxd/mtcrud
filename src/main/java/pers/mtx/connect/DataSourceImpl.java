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

public class DataSourceImpl implements DataSource {

    private static DataSourceImpl dataSource = new DataSourceImpl();
    private ReentrantLock lock = new ReentrantLock();



    //定义连接池中连接对象的存储容器
    private List<PoolConnection> list = Collections.synchronizedList(new ArrayList<>());


    //定义数据库连接属性
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/crud";
    private static String USERNAME = "root";
    private static String PASSWORD = "mtx990812";

    //定义默认连接池属性配置
    private static int initSize = 2;
    private static int maxSize = 4;
    private static int stepSize = 1;
    private static int timeout = 2000;

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
//        String init = null;
//        String step = null;
//        String max = null;
//        String time = null;

//        initSize = init == null ? initSize : Integer.parseInt(init);
//        maxSize = max == null ? maxSize : Integer.parseInt(max);
//        stepSize = step == null ? stepSize : Integer.parseInt(step);
//        timeout = time == null ? timeout : Integer.parseInt(time);

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
                //System.out.println("初始化了" + (i + 1) + "个连接");
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
