package pers.mtx.mt.data.sql;

import pers.mtx.init.entity.Root;
import pers.mtx.mt.data.sql.entity.PostParams;
import pers.mtx.util.IdWorker;

import java.util.Map;
import java.util.Objects;

/**
 * HTTP 简单添加的sql生成类
 */
public class MtPostSql {
    public static String spliceSql(PostParams params){
        StringBuilder sb = new StringBuilder("Insert into ");
        String dbName = Root.getNodeName(Integer.valueOf(params.getDbName()));
        String tbName = Root.getNodeName(Integer.valueOf(params.getTbName()));
        sb.append(dbName).append(".").append(tbName);
        StringBuilder valueBuilder = new StringBuilder();
        sb.append("(");
        valueBuilder.append("(");
        if (!params.getId().isEmpty()){
            long id = IdWorker.idWorker.nextId();
            String idName = Root.getNodeName(Integer.parseInt(params.getId()));
            sb.append(idName).append(",");
            valueBuilder.append(id).append(",");
        }
        for (int i = 0; i < params.getValueMap().size(); i++) {
            Map.Entry<String, String> entry = params.getValueMap().get(i);
            String colName = Root.getNodeName(Integer.parseInt(entry.getKey()));
            sb.append(colName).append(",");
            valueBuilder.append(formatValue(entry.getKey(),entry.getValue())).append(",");
        }
        sb.delete(sb.length()-1,sb.length());
        valueBuilder.delete(valueBuilder.length()-1,valueBuilder.length());
        sb.append(") VALUES ").append(valueBuilder).append(");");
        return sb.toString();
    }

    public static String spliceSqlWithId(PostParams params,String id){
        StringBuilder sb = new StringBuilder("Insert into ");
        String dbName = Root.getNodeName(Integer.valueOf(params.getDbName()));
        String tbName = Root.getNodeName(Integer.valueOf(params.getTbName()));
        sb.append(dbName).append(".").append(tbName);
        StringBuilder valueBuilder = new StringBuilder();
        sb.append("(");
        valueBuilder.append("(");
        if (!params.getId().isEmpty()){
            String idName = Root.getNodeName(Integer.parseInt(params.getId()));
            sb.append(idName).append(",");
            valueBuilder.append(id).append(",");
        }
        for (int i = 0; i < params.getValueMap().size(); i++) {
            Map.Entry<String, String> entry = params.getValueMap().get(i);
            String colName = Root.getNodeName(Integer.parseInt(entry.getKey()));
            sb.append(colName).append(",");
            valueBuilder.append(formatValue(entry.getKey(),entry.getValue())).append(",");
        }
        sb.delete(sb.length()-1,sb.length());
        valueBuilder.delete(valueBuilder.length()-1,valueBuilder.length());
        sb.append(") VALUES ").append(valueBuilder).append(");");
        return sb.toString();
    }

    public static String formatValue(String rank,String value){
        String type = Root.getColType(Integer.valueOf(rank));
        //System.out.println(rank+";"+value+";"+type);
        if (Objects.equals(type, "varchar")) return "'"+value+"'";
        return value;
    }
}
