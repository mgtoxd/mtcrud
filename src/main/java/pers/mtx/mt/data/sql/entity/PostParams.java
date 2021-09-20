package pers.mtx.mt.data.sql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostParams extends Params {

    private String id;
    // 创建时间
    private String ct;
    private String ut;
    private ArrayList<Map.Entry<String,String>> valueMap;

    @Override
    public String toString() {
        return super.toString()+"PostParams{" +
                "id='" + id + '\'' +
                ", ct='" + ct + '\'' +
                ", ut='" + ut + '\'' +
                ", valueMap=" + valueMap +
                '}';
    }
}
