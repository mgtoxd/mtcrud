package pers.mtx.mt.data.sql.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class GetParams extends RudParams {
    private HashMap<String,String> order = null;
    private String limit = null;
    private List<String> cols = null;

    @Override
    public String toString() {
        return "GetParams{" +
                "order='" + order + '\'' +
                ", orderAsc='"  + '\'' +
                ", limit=" + limit +
                ", cols=" + cols +
                " " + super.toString();
    }
}
