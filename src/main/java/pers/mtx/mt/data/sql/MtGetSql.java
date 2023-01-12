package pers.mtx.mt.data.sql;

import pers.mtx.init.entity.Root;
import pers.mtx.mt.data.sql.entity.GetParams;

import java.util.HashMap;
import java.util.Objects;

/**
 * HTTP 简单查询的sql生成类
 */
public class MtGetSql {
    public static String spliceSql(GetParams params){
        StringBuilder sb = new StringBuilder("Select ");
        for (String str :  params.getCols()) {
            sb.append("`").append(Root.getNodeName(Integer.valueOf(str))).append("`");
            sb.append(",");
        }
        sb.replace(sb.length()-1,sb.length()," ");
        sb.append("from ").append(Root.getNodeName(Integer.valueOf(params.getDbName()))).append(".").append(Root.getNodeName(Integer.valueOf(params.getTbName())));

        if (params.getEq()!=null&&!params.getEq().isEmpty()) mapToString(params.getEq(), sb,"=");
        if (params.getLt()!=null&&!params.getLt().isEmpty()) mapToString(params.getLt(), sb,"<");
        if (params.getGt()!=null&&!params.getGt().isEmpty()) mapToString(params.getGt(), sb,">");
        if (params.getLike()!=null&&!params.getLike().isEmpty()) likeMapToString(params.getLike(), sb);
        if (sb.indexOf(" where ")!=-1) sb.delete(sb.length()-4,sb.length());
        if (params.getOrder()!=null&&!params.getOrder().isEmpty()) orderMapToString(params.getOrder(), sb);
        if (sb.indexOf("order by")!=-1) sb.delete(sb.length()-2,sb.length());
        if (params.getLimit()!=null&&!params.getLimit().isEmpty()) sb.append(" limit ").append(params.getLimit());
        return sb +";";
    }

    private static void mapToString(HashMap<String,String> params, StringBuilder sb,String ins) {
        if (sb.indexOf(" where ")==-1) sb.append(" where ");
        params.forEach((key, value) -> sb.append("`").append(Root.getNodeName(Integer.valueOf(key))).append("`").append(ins).append(formatValue(key,value)).append(" and "));
    }

    private static void likeMapToString(HashMap<String,String> params, StringBuilder sb) {
        if (sb.indexOf(" where ")==-1) sb.append(" where ");
        params.forEach((key, value) -> sb.append("`").append(Root.getNodeName(Integer.valueOf(key))).append("`").append(" like ").append("'").append(value).append("'").append(" and "));
    }

    private static void orderMapToString(HashMap<String,String> params, StringBuilder sb) {
        sb.append(" order by ");
        params.forEach((key, value) -> {
            sb.append("`").append(Root.getNodeName(Integer.valueOf(key))).append("`");
            //System.out.println(value);
            if (value.equals("A")) {
                sb.append(" ASC");
            }else {
                sb.append(" DESC");
            }
            sb.append(", ");
        });
    }

    public static String formatValue(String rank,String value){
        String type = Root.getColType(Integer.valueOf(rank));
        //System.out.println(rank+";"+value+";"+type);
        if (Objects.equals(type, "varchar")) return "'"+value+"'";
        return value;
    }
}
