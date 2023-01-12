package pers.mtx.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

/**
 * JSON 工具类
 */
public class JsonUtil {

    /**
     * 格式化JSON 使易读
     * @param str 待格式化json
     * @return 带有换行等的json
     */
    public static String formatJson(String str){
        StringBuilder builder = new StringBuilder(str);
        int tab = 0;
        for (int i = 0; i < builder.length(); i++) {

            if (builder.charAt(i)=='{'||builder.charAt(i)=='['){
                builder.insert(i+1,'\n');
                tab++;
                for (int j = 0; j < tab; j++) {
                    builder.insert(i+2+j,'\t');
                }

                i+=tab+1;
            }else if (builder.charAt(i)=='}'||builder.charAt(i)==']'){
                builder.insert(i,'\n');
                tab--;
                for (int j = 0; j < tab; j++) {
                    builder.insert(i+1+j,'\t');
                }
                i+=tab+1;
            }else if (builder.charAt(i)==','){
                builder.insert(i+1,'\n');
                for (int j = 0; j < tab; j++) {
                    builder.insert(i+2+j,'\t');
                }
                i+=tab+1;
            }
        }
        return builder.toString();
    }

    /**
     * ResultSet转JSON
     * @param resultSet rs
     * @return Json 字符串
     */
    public static String resultSetToJson(ResultSet resultSet) throws SQLException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int colCount = metaData.getColumnCount();
        while (resultSet.next()){
            ObjectNode node = mapper.createObjectNode();

            for (int i = 1; i <= colCount ; i++) {
                node.put(metaData.getColumnLabel(i),resultSet.getString(i) );
            }
            arrayNode.add(node);
        }
        return arrayNode.toString();
    }


}
