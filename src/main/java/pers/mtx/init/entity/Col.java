package pers.mtx.init.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

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

    @Override
    public ArrayList<? extends INode> mgetList() {
        return null;
    }
}
