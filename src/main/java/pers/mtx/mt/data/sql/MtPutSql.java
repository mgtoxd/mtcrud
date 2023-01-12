package pers.mtx.mt.data.sql;

import pers.mtx.init.entity.Root;
import pers.mtx.mt.data.sql.entity.PutParams;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * HTTP 简单修改的sql生成类
 */
public class MtPutSql {
    public static String spliceSql(PutParams params) {
        //System.out.println(params);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("update ");
        stringBuilder.append(Root.getNodeName(Integer.valueOf(params.getDbName()))).append(".").append(Root.getNodeName(Integer.valueOf(params.getTbName()))).append(" set ");
        for (int i = 0; i < params.getValueMap().size(); i++) {
            Map.Entry<String, String> entry = params.getValueMap().get(i);
            stringBuilder.append(Root.getNodeName(Integer.parseInt(entry.getKey()))).append(" = ").append(formatValue(entry.getKey(), entry.getValue())).append(",");
        }
        stringBuilder.delete(stringBuilder.length()-1,stringBuilder.length());
//        stringBuilder.append("where");
        if (params.getCondition().getEq()!=null&&!params.getCondition().getEq().isEmpty()) mapToString(params.getCondition().getEq(), stringBuilder,"=");
        if (params.getCondition().getLt()!=null&&!params.getCondition().getLt().isEmpty()) mapToString(params.getCondition().getLt(), stringBuilder,"<");
        if (params.getCondition().getGt()!=null&&!params.getCondition().getGt().isEmpty()) mapToString(params.getCondition().getGt(), stringBuilder,">");
        if (params.getCondition().getLike()!=null&&!params.getCondition().getLike().isEmpty()) likeMapToString(params.getCondition().getLike(), stringBuilder);
        if (stringBuilder.indexOf(" where ")!=-1) stringBuilder.delete(stringBuilder.length()-4,stringBuilder.length());
        return stringBuilder.toString();
    }


    private static void mapToString(HashMap<String,String> params, StringBuilder sb, String ins) {
        if (sb.indexOf(" where ")==-1) sb.append(" where ");
        params.forEach((key, value) -> sb.append("`").append(Root.getNodeName(Integer.valueOf(key))).append("`").append(ins).append(formatValue(key,value)).append(" and "));
    }

    private static void likeMapToString(HashMap<String,String> params, StringBuilder sb) {
        if (sb.indexOf(" where ")==-1) sb.append(" where ");
        params.forEach((key, value) -> sb.append("`").append(Root.getNodeName(Integer.valueOf(key))).append("`").append(" like ").append("'").append(value).append("'").append(" and "));
    }
    public static String formatValue(String rank, String value) {
        String type = Root.getColType(Integer.valueOf(rank));
        //System.out.println(rank + ";" + value + ";" + type);
        if (Objects.equals(type, "varchar")) return "'" + value + "'";
        return value;
    }
}
