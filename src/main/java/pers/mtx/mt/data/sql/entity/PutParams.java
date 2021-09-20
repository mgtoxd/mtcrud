package pers.mtx.mt.data.sql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PutParams extends Params {
    private Condition condition;
    private ArrayList<Map.Entry<String, String>> valueMap;

    @Override
    public String toString() {
        return super.toString() + "PutParams{" +
                "condition=" + condition +
                ", valueMap=" + valueMap +
                '}';
    }

}
