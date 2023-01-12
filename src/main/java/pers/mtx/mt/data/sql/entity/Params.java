package pers.mtx.mt.data.sql.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
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
