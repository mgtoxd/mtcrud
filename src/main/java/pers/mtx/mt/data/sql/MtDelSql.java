package pers.mtx.mt.data.sql;

import pers.mtx.init.entity.Root;
import pers.mtx.mt.data.sql.entity.DelParams;
import pers.mtx.mt.data.sql.entity.GetParams;

import java.util.HashMap;

public class MtDelSql {

    public static String spliceSql(DelParams params){
        StringBuilder sb = new StringBuilder();
        sb.append("delete from ").append(params.getDbName()).append(".").append(params.getTbName());
        if (params.getEq()!=null) mapToString(params.getEq(), sb,"=");
        if (params.getLt()!=null) mapToString(params.getLt(), sb,"<");
        if (params.getGt()!=null) mapToString(params.getGt(), sb,">");
        if (params.getLike()!=null) likeMapToString(params.getLike(), sb," like ");
        if (sb.indexOf(" where ")!=-1) sb.delete(sb.length()-4,sb.length());
        return sb.toString();
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
