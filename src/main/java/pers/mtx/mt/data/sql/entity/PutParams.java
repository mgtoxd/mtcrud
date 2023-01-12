package pers.mtx.mt.data.sql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
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
