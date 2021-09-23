package pers.mtx.mt.data.sql;

import pers.mtx.init.entity.Root;
import pers.mtx.mt.data.sql.entity.GetParams;

import java.util.HashMap;
import java.util.Iterator;

public class MtGetSql {
    public static String spliceSql(GetParams params){
        StringBuilder sb = new StringBuilder("Select ");
        Iterator<String> iterator = params.getCols().iterator();
        for (String str :  params.getCols()) {
            sb.append("`").append(str).append("`");
            sb.append(",");
        }
        sb.replace(sb.length()-1,sb.length()," ");
        sb.append("from ").append(params.getDbName()).append(".").append(params.getTbName());

        if (params.getEq()!=null) mapToString(params.getEq(), sb,"=");
        if (params.getLt()!=null) mapToString(params.getLt(), sb,"<");
        if (params.getGt()!=null) mapToString(params.getGt(), sb,">");
        if (params.getLike()!=null) likeMapToString(params.getLike(), sb," like ");
        if (sb.indexOf(" where ")!=-1) sb.delete(sb.length()-4,sb.length());
        if (params.getOrder()!=null) orderMapToString(params.getOrder(), sb);
        if (sb.indexOf("order by")!=-1) sb.delete(sb.length()-2,sb.length());
        if (params.getLimit()!=null) sb.append(" limit ").append(params.getLimit());
        return sb.toString()+";";
    }

    private static void mapToString(HashMap<String,String> params, StringBuilder sb,String ins) {
        if (sb.indexOf(" where ")==-1) sb.append(" where ");
        params.forEach((key, value) -> sb.append("`").append(Root.getNodeName(Integer.valueOf(key))).append("`").append(ins).append(formatValue(key,value)).append(" and "));
    }

    private static void likeMapToString(HashMap<String,String> params, StringBuilder sb,String ins) {
        if (sb.indexOf(" where ")==-1) sb.append(" where ");
        params.forEach((key, value) -> sb.append("`").append(Root.getNodeName(Integer.valueOf(key))).append("`").append(ins).append("'").append(value).append("'").append(" and "));
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
        if (type.equals("varchar")) return "'"+value+"'";
        return value;
    }
}
