package pers.mtx.mt.data.sql.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.HashMap;
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public abstract class  RudParams extends Params {
    private HashMap<String,String> eq;
    private HashMap<String,String> gt;
    private HashMap<String,String> lt;
    private HashMap<String,String> like;

    @Override
    public String toString() {
        return
                "eq=" + eq +
                ", gt=" + gt +
                ", lt=" + lt +
                ", like=" + like
                 + super.toString();
    }
}
