package pers.mtx.mt;

import io.netty.handler.codec.http.HttpMethod;
import pers.mtx.mt.crud.MtDel;
import pers.mtx.mt.crud.MtGet;
import pers.mtx.mt.crud.MtPost;
import pers.mtx.mt.crud.MtPut;

import javax.swing.*;
import java.util.HashMap;

public class CrudFactory {
    private static final HashMap<String, Crud> map = new HashMap<>();
    static {
        map.put("GET",new MtGet());
        map.put("PUT",new MtPut());
        map.put("POST",new MtPost());
        map.put("DELETE",new MtDel());
    }
    public static Crud getCrud(HttpMethod method){
        return map.get(method.name());
    }
}
