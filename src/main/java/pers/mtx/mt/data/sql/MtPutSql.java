package pers.mtx.mt.data.sql;

import pers.mtx.init.entity.Root;
import pers.mtx.mt.data.sql.entity.PostParams;
import pers.mtx.mt.data.sql.entity.PutParams;

import java.util.HashMap;
import java.util.Map;

public class MtPutSql {
    public static String spliceSql(PutParams params) {
        System.out.println(params);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("update ");
        stringBuilder.append(params.getDbName()).append(".").append(params.getTbName()).append(" set ");
        for (int i = 0; i < params.getValueMap().size(); i++) {
            Map.Entry<String, String> entry = params.getValueMap().get(i);
            stringBuilder.append(Root.getNodeName(Integer.parseInt(entry.getKey()))).append(" = ").append(formatValue(entry.getKey(), entry.getValue())).append(",");
        }
        stringBuilder.delete(stringBuilder.length()-1,stringBuilder.length());
//        stringBuilder.append("where");
        if (params.getCondition().getEq()!=null) mapToString(params.getCondition().getEq(), stringBuilder,"=");
        if (params.getCondition().getLt()!=null) mapToString(params.getCondition().getLt(), stringBuilder,"<");
        if (params.getCondition().getGt()!=null) mapToString(params.getCondition().getGt(), stringBuilder,">");
        if (params.getCondition().getLike()!=null) likeMapToString(params.getCondition().getLike(), stringBuilder," like ");
        stringBuilder.delete(stringBuilder.length()-4,stringBuilder.length());
        return stringBuilder.toString();
    }


    private static void mapToString(HashMap<String,String> params, StringBuilder sb, String ins) {
        if (sb.indexOf(" where ")==-1) sb.append(" where ");
        params.forEach((key, value) -> sb.append("`").append(Root.getNodeName(Integer.valueOf(key))).append("`").append(ins).append(formatValue(key,value)).append(" and "));
    }

    private static void likeMapToString(HashMap<String,String> params, StringBuilder sb,String ins) {
        if (sb.indexOf(" where ")==-1) sb.append(" where ");
        params.forEach((key, value) -> sb.append("`").append(Root.getNodeName(Integer.valueOf(key))).append("`").append(ins).append("'").append(value).append("'").append(" and "));
    }
    public static String formatValue(String rank, String value) {
        String type = Root.getColType(Integer.valueOf(rank));
        System.out.println(rank + ";" + value + ";" + type);
        if (type.equals("varchar")) return "'" + value + "'";
        return value;
    }
}
