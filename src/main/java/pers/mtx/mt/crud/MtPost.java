package pers.mtx.mt.crud;

import com.fasterxml.jackson.databind.ObjectMapper;
import pers.mtx.connect.DataSourceImpl;
import pers.mtx.connect.PoolConnection;
import pers.mtx.mt.Crud;
import pers.mtx.mt.data.sql.MtPostSql;
import pers.mtx.mt.data.sql.entity.PostParams;
import pers.mtx.util.BeanMapper;
import pers.mtx.util.LogUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MtPost implements Crud {
    @Override
    public String make(String uri, byte[] content) throws IOException, SQLException {
        ObjectMapper mapper = new ObjectMapper();
        //解析uri

        String[] split = uri.replaceFirst("/mt/", "").split("/");
        //System.out.println(Arrays.toString(content));
        PostParams postParams = mapper.readValue(content, PostParams.class);
        postParams.setDbName(split[0]);
        postParams.setTbName(split[1]);
        return execSql(postParams);
    }

    @Override
    public String make(pers.mtx.grpc.mtcrud.PostParams getParamsProt) {
        PostParams params = BeanMapper.map(getParamsProt, PostParams.class);
        try {
            return execSql(params);
        }catch (Exception e){
            LogUtil.getExceptionInfo(e);
        }
        return "f";
    }

    /**
     * 执行插入操作sql
     * @param postParams 增加参数
     * @return t或者f
     */
    private String execSql(PostParams postParams) throws SQLException {
        String sql = MtPostSql.spliceSql(postParams);
        PoolConnection poolCon = DataSourceImpl.getConnection();
        Connection connection = poolCon.getConnect();
        Statement statement = connection.createStatement();
        try {
            statement.executeUpdate(sql);
            return "t";
        } catch (Exception e) {
            LogUtil.getExceptionInfo(e);
            return "f";
        } finally {
            poolCon.releaseConnect();
        }
    }
}
