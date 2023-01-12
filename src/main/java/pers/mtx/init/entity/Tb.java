package pers.mtx.init.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class Tb extends INode {
    private String tableName;
    private ArrayList<Col> cols;

    public Tb(String tableName,Integer rank) {
        this.rank = rank;
        this.tableName = tableName;
        this.cols = new ArrayList<>();
    }

    @Override
    public String mgetMark() {
        return getTableName();
    }

}
