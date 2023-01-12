package pers.mtx.mt;

import io.netty.handler.codec.http.HttpMethod;
import pers.mtx.mt.crud.*;

import java.util.HashMap;


/**
 * 结合策略模式和工厂模式选择不同的操作逻辑
 */
public class CrudFactory {
    private static final HashMap<String, Crud> map = new HashMap<>();
    static {
        map.put("GET",new MtGet());
        map.put("PUT",new MtPut());
        map.put("POST",new MtPost());
        map.put("DELETE",new MtDel());
        map.put("mget",new MGet());
        map.put("AtDEL",new AtDel());
        map.put("AtPut",new AtPost());
        map.put("AtPost",new AtPut());
    }

    /**
     * HTTP 协议通过请求类型获取操作类
     * @param method http方法
     * @return 具体操作类
     */
    public static Crud getCrud(HttpMethod method){
        return map.get(method.name());
    }

    /**
     * 通过名称获取操作类
     * @param crudName 操作类的名称
     * @return 具体操作类
     */
    public static Crud getCrudByName(String crudName){
        return map.get(crudName);
    }
}
