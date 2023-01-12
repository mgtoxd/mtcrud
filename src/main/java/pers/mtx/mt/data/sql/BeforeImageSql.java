package pers.mtx.mt.data.sql;

import pers.mtx.init.entity.Root;
import pers.mtx.mt.data.sql.entity.DelParams;
import pers.mtx.mt.data.sql.entity.PostParams;
import pers.mtx.mt.data.sql.entity.PutParams;

import java.util.HashMap;
import java.util.Objects;


/**
 * 生成 AT 事务模式下 before image sql 的类
 */
public class BeforeImageSql {

    /**
     * 删除
     * @param params 删除参数
     * @return 删除逻辑的before image查询语句
     */
    public static String spliceSql(DelParams params) {
        StringBuilder sb = new StringBuilder();
        sb.append("select *,_rowid from ").append(Root.getNodeName(Integer.valueOf(params.getDbName()))).append(".").append(Root.getNodeName(Integer.valueOf(params.getTbName())));
        if (params.getEq() != null && !params.getEq().isEmpty()) mapToString(params.getEq(), sb, "=");
        if (params.getLt() != null && !params.getLt().isEmpty()) mapToString(params.getLt(), sb, "<");
        if (params.getGt() != null && !params.getGt().isEmpty()) mapToString(params.getGt(), sb, ">");
        if (params.getLike() != null && !params.getLike().isEmpty()) likeMapToString(params.getLike(), sb);
        if (sb.indexOf(" where ") != -1) sb.delete(sb.length() - 4, sb.length());
        sb.append("order by _rowid;");
        return sb.toString();
    }

    /**
     * 修改
     * @param params 修改参数
     * @return 修改逻辑的image 查询语句
     */
    public static String spliceSql(PutParams params) {
        StringBuilder sb = new StringBuilder();
        sb.append("select *,_rowid from ").append(Root.getNodeName(Integer.valueOf(params.getDbName()))).append(".").append(Root.getNodeName(Integer.valueOf(params.getTbName())));
        if (params.getCondition().getEq() != null && !params.getCondition().getEq().isEmpty()) mapToString(params.getCondition().getEq(), sb, "=");
        if (params.getCondition().getLt() != null && !params.getCondition().getLt().isEmpty()) mapToString(params.getCondition().getLt(), sb, "<");
        if (params.getCondition().getGt() != null && !params.getCondition().getGt().isEmpty()) mapToString(params.getCondition().getGt(), sb, ">");
        if (params.getCondition().getLike() != null && !params.getCondition().getLike().isEmpty()) likeMapToString(params.getCondition().getLike(), sb);
        if (sb.indexOf(" where ") != -1) sb.delete(sb.length() - 4, sb.length());
        sb.append("order by _rowid;");
        return sb.toString();
    }

    /**
     * 添加
     * @param params 增加参数
     * @param rowId 行id
     * @return 增加业务的image sql
     */
    public static String spliceSql(PostParams params, Long rowId) {
        return "select *,_rowid from " +
                Root.getNodeName(Integer.valueOf(params.getDbName())) + "." +
                Root.getNodeName(Integer.valueOf(params.getTbName())) + " where _rowid=" + rowId;
    }

    private static void mapToString(HashMap<String, String> params, StringBuilder sb, String ins) {
        if (sb.indexOf(" where ") == -1) sb.append(" where ");
        params.forEach((key, value) -> sb.append("`").append(Root.getNodeName(Integer.valueOf(key))).append("`").append(ins).append(formatValue(key, value)).append(" and "));
    }

    private static void likeMapToString(HashMap<String, String> params, StringBuilder sb) {
        if (sb.indexOf(" where ") == -1) sb.append(" where ");
        params.forEach((key, value) -> sb.append("`").append(Root.getNodeName(Integer.valueOf(key))).append("`").append(" like ").append("'").append(value).append("'").append(" and "));
    }

    public static String formatValue(String rank, String value) {
        String type = Root.getColType(Integer.valueOf(rank));
        //System.out.println(rank+";"+value+";"+type);
        if (Objects.equals(type, "varchar")) return "'" + value + "'";
        return value;
    }
}
