package pers.mtx.mt.crud;

import com.fasterxml.jackson.databind.ObjectMapper;
import pers.mtx.connect.DataSourceImpl;
import pers.mtx.connect.PoolConnection;
import pers.mtx.init.entity.Root;
import pers.mtx.mt.Crud;
import pers.mtx.mt.data.sql.MtGetSql;
import pers.mtx.mt.data.sql.entity.GetParams;
import pers.mtx.util.JsonUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MtGet implements Crud {
    @Override
    public String make(String uri, byte[] content) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //解析content
        GetParams value = mapper.readValue(content, GetParams.class);
        //解析uri
        String[] split = uri.replaceFirst("/mt/", "").split("/");
        value.setDbName(split[0]);
        value.setTbName(split[1]);
        List<String> cols = Arrays.stream(split[2].split("/?")[0].split(";")).map(e -> Root.getNodeName(Integer.valueOf(e))).collect(Collectors.toList());
        try {
            if (split.length > 3) {
                // 有分页
                value.setLimit("" + (Integer.parseInt(split[4]) * (Integer.parseInt(split[3]) - 1)) + "," + split[4]);
            }
        } catch (Exception e) {
            return "分页出现问题";
        }

        value.setCols(cols);

        PoolConnection connection = DataSourceImpl.getConnection();

        String sql = MtGetSql.spliceSql(value);
        try (Statement statement = connection.getConnect().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            return JsonUtil.resultSetToJson(resultSet);
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            connection.releaseConnect();
        }

    }


}
