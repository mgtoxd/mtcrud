package pers.mtx.mt.crud;

import com.fasterxml.jackson.databind.ObjectMapper;
import pers.mtx.connect.DataSourceImpl;
import pers.mtx.connect.PoolConnection;
import pers.mtx.init.entity.Root;
import pers.mtx.mt.Crud;
import pers.mtx.mt.data.sql.MtDelSql;
import pers.mtx.mt.data.sql.entity.DelParams;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class MtDel implements Crud {
    @Override
    public String make(String uri,byte[] content) throws IOException, SQLException {
        ObjectMapper mapper = new ObjectMapper();
        String[] split = uri.replaceFirst("/mt/","").split("/");
        DelParams delParams = mapper.readValue(content, DelParams.class);
        delParams.setDbName(Root.getNodeName(Integer.valueOf(split[0])));
        delParams.setTbName(Root.getNodeName(Integer.valueOf(split[1])));

        String sql = MtDelSql.spliceSql(delParams);
        PoolConnection poolConnection = DataSourceImpl.getConnection();
        Statement statement = poolConnection.getConnect().createStatement();
        try {
            statement.executeUpdate(sql);
            return "t";
        }catch (Exception e){
            //System.out.println(e);
            return "f";
        }finally {
            poolConnection.releaseConnect();
        }
    }

    private static void mapToString(HashMap<String,String> params, StringBuilder sb, String ins) {
        if (sb.indexOf(" where ")==-1) sb.append(" where ");
        params.forEach((key, value) -> sb.append("`").append(Root.getNodeName(Integer.valueOf(key))).append("`").append(ins).append(formatValue(key,value)).append(" and "));
    }

    private static void likeMapToString(HashMap<String,String> params, StringBuilder sb,String ins) {
        if (sb.indexOf(" where ")==-1) sb.append(" where ");
        params.forEach((key, value) -> sb.append("`").append(Root.getNodeName(Integer.valueOf(key))).append("`").append(ins).append("'").append(value).append("'").append(" and "));
    }

    public static String formatValue(String rank,String value){
        String type = Root.getColType(Integer.valueOf(rank));
        //System.out.println(rank+";"+value+";"+type);
        if (type.equals("varchar")) return "'"+value+"'";
        return value;
    }
}
