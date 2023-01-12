package pers.mtx.init.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 数据库中列的属性类
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class Col extends INode {
    private String colName;
    private String dataType;

    public Col(String colName, String dataType,Integer rank) {
        this.rank = rank;
        this.colName = colName;
        this.dataType = dataType;
    }

    @Override
    public String mgetMark() {
        return getColName();
    }

}
