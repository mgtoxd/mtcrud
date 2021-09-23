package pers.mtx.mt.crud;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import pers.mtx.connect.DataSourceImpl;
import pers.mtx.connect.PoolConnection;
import pers.mtx.init.entity.Root;
import pers.mtx.mt.Crud;
import pers.mtx.mt.data.sql.MtPutSql;
import pers.mtx.mt.data.sql.entity.PutParams;

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
        params.setDbName(Root.getNodeName(Integer.valueOf(split[0])));
        params.setTbName(Root.getNodeName(Integer.valueOf(split[1])));
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
