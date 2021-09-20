package pers.mtx.mt.data.sql.entity;

import lombok.Data;

@Data
public abstract class Params {
    private String dbName;
    private String tbName;

    @Override
    public String toString() {
        return
                "dbName='" + dbName + '\'' +
                ", tbName='" + tbName + '\'' +
                '}';
    }
}
