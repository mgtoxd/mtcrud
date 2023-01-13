import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pers.mtx.mt.data.sql.entity.Condition;
import pers.mtx.mt.data.sql.entity.PutParams;

import java.util.ArrayList;
import java.util.Map;

public class java2Json {

    public static void main(String[] args) throws JsonProcessingException {
        ArrayList<Map.Entry<String, String>> objects = new ArrayList<>();
        objects.add(Map.entry("a","a"));
        PutParams putParams = new PutParams().setCondition(new Condition()).setValueMap(objects);

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(        mapper.writeValueAsString(putParams));
    }
}
