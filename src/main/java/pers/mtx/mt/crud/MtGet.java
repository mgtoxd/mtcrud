package pers.mtx.mt.crud;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pers.mtx.connect.DataSourceImpl;
import pers.mtx.connect.PoolConnection;
import pers.mtx.util.JsonUtil;
import pers.mtx.init.entity.Root;
import pers.mtx.mt.Crud;
import pers.mtx.mt.data.sql.MtGetSql;
import pers.mtx.mt.data.sql.entity.GetParams;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

public class MtGet implements Crud {
    @Override
    public String make(String uri,byte[] content) throws IOException, SQLException {
        ObjectMapper mapper = new ObjectMapper();
        //解析content
        GetParams value = mapper.readValue(content, GetParams.class);
        //解析uri
        String[] split = uri.replaceFirst("/mt/","").split("/");
        value.setDbName(Root.getNodeName(Integer.valueOf(split[0])));
        value.setTbName(Root.getNodeName(Integer.valueOf(split[1])));
        List<String> cols = Arrays.stream(split[2].split(";")).map(e -> Root.getNodeName(Integer.valueOf(e))).collect(Collectors.toList());

        value.setCols(cols);

        //System.out.println("test"+value.toString());
        JsonNode jsonNode = mapper.readTree(content);

        //System.out.println(value.toString());
        //System.out.println();
        PoolConnection connection = DataSourceImpl.getConnection();
        Statement statement = connection.getConnect().createStatement();
        String sql = MtGetSql.spliceSql(value);
        //System.out.println(sql);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            return JsonUtil.resultSetToJson(resultSet);
        }catch (Exception e){
            return e.getMessage();
        }finally {
            connection.releaseConnect();
        }

    }





}
