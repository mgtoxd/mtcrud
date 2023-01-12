package pers.mtx.init.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;


/**
 * 数据库中库的属性类
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class Db extends INode {
    private String dbName;
    private ArrayList<Tb> tables;

    public Db(String dbName,Integer rank) {
        this.dbName = dbName;
        this.rank = rank;
        this.tables = new ArrayList<>();
    }

    @Override
    public String mgetMark() {
        return getDbName();
    }

}
