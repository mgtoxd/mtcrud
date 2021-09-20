package pers.mtx.init.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class Db extends INode {
    private String dbName;
    private ArrayList<Tb> tables;

    public Db(String dbName,Integer rank) {
        this.dbName = dbName;
        this.rank = rank;
        this.tables = new ArrayList<Tb>();
    }

    @Override
    public String mgetMark() {
        return getDbName();
    }

    @Override
    public ArrayList<? extends INode> mgetList() {
        return getTables();
    }
}
