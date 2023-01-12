package pers.mtx.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;



public class ResultSetConvertUtil {

    /**
     * 将查询结果resultset转换为List<Map>
     */
    public static List<Map<String,String>> convertListAndValueStr(ResultSet rs) throws SQLException {
        List<Map<String,String>> list = new ArrayList<>();
        ResultSetMetaData md = rs.getMetaData();//获取键名
        int columnCount = md.getColumnCount();//获取列的数量
        while (rs.next()) {
            Map<String,String> rowData = new HashMap<>();//声明Map
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnLabel(i), Objects.nonNull(rs.getObject(i))?rs.getObject(i).toString():"");//获取键名及值
            }
            list.add(rowData);
        }
        return list;
    }
}
