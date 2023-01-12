package pers.mtx.mt.crud;

import com.fasterxml.jackson.databind.ObjectMapper;
import pers.mtx.connect.DataSourceImpl;
import pers.mtx.connect.PoolConnection;
import pers.mtx.mt.Crud;
import pers.mtx.mt.data.sql.MtPutSql;
import pers.mtx.mt.data.sql.entity.PutParams;
import pers.mtx.util.BeanMapper;
import pers.mtx.util.LogUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MtPut implements Crud {
    @Override
    public String make(String uri,byte[] content) throws IOException, SQLException {
        ObjectMapper mapper = new ObjectMapper();
        String[] split = uri.replaceFirst("/mt/","").split("/");

        PutParams params = mapper.readValue(content, PutParams.class);
        params.setDbName(split[0]);
        params.setTbName(split[1]);
        return execSql(params);
    }

    @Override
    public String make(pers.mtx.grpc.mtcrud.PutParams getParamsProt) {
        PutParams params = BeanMapper.map(getParamsProt, PutParams.class);
        try {
            return execSql(params);
        }catch (Exception e){
            LogUtil.getExceptionInfo(e);
        }
        return "f";
    }

    /**
     * 执行update操作sql
     * @param params 修改参数
     * @return t或者f
     */
    private String execSql(PutParams params) throws SQLException {
        String sql = MtPutSql.spliceSql(params);

        PoolConnection connection = DataSourceImpl.getConnection();
        Connection connect = connection.getConnect();
        Statement statement = connect.createStatement();
        //System.out.println(sql);
        try {
            statement.executeUpdate(sql);
            return "t";
        }catch (Exception e){
            //System.out.println(e);
            return "f";
        }finally {
            connection.releaseConnect();
        }
    }
}
