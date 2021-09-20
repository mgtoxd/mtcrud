package pers.mtx.mt.crud;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pers.mtx.connect.DataSourceImpl;
import pers.mtx.connect.PoolConnection;
import pers.mtx.init.DataStructure;
import pers.mtx.init.entity.Root;
import pers.mtx.mt.Crud;
import pers.mtx.mt.data.sql.MtPostSql;
import pers.mtx.mt.data.sql.entity.GetParams;
import pers.mtx.mt.data.sql.entity.PostParams;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedHashMap;

public class MtPost implements Crud {
    @Override
    public String make(String uri,byte[] content) throws IOException, SQLException {
        ObjectMapper mapper = new ObjectMapper();
        //解析uri

        String[] split = uri.replaceFirst("/mt/","").split("/");
        System.out.println(Arrays.toString(content));
        PostParams postParams = mapper.readValue(content, PostParams.class);
        postParams.setDbName(Root.getNodeName(Integer.valueOf(split[0])));
        postParams.setTbName(Root.getNodeName(Integer.valueOf(split[1])));
        System.out.println(postParams);
        String sql = MtPostSql.spliceSql(postParams);
        System.out.println("da");
        System.out.println(sql);
        PoolConnection poolCon = DataSourceImpl.getConnection();
        Connection connection = poolCon.getConnect();
        Statement statement = connection.createStatement();
        try {
            statement.executeUpdate(sql);
            return "t";
        }catch (Exception e){
            System.out.println(e);
            return "f";
        }finally {
            poolCon.releaseConnect();
        }
    }
}
