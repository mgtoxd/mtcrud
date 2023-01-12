package pers.mtx.mt;

import pers.mtx.grpc.mtcrud.DelParams;
import pers.mtx.grpc.mtcrud.GetParams;
import pers.mtx.grpc.mtcrud.PostParams;
import pers.mtx.grpc.mtcrud.PutParams;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface Crud {

    /**
     * （HTTP协议）依据请求的url和携带的请求体进行操作逻辑
     * @param uri url
     * @param content 请求体
     * @return 1. 查询请求返回数据的JSON
     *         2. 其余请求返回“t” 或者 “f”
     */
    default String make(String uri,byte[] content) throws IOException, SQLException{return null;}
    /**
     * (GRPC) 返回查询的List<Map>形势
     * @param getParamsProt grpc 查询参数
     * @return 数据
     */
    default List<Map<String,String>> make(GetParams getParamsProt){
        return null;
    }

    /**
     * (AT/GRPC) 执行增加请求
     * @param postParams grpc 增加参数
     * @return t/f
     */
    default String make(PostParams postParams){
        return null;
    }

    /**
     * (AT/GRPC) 执行修改请求
     * @param putParams grpc 更新参数
     * @return t/f
     */
    default String make(PutParams putParams){
        return null;
    }

    /**
     * (AT/GRPC) 执行删除请求
     * @param delParams grpc 删除参数
     * @return t/f
     */
    default String make(DelParams delParams){
        return null;
    }
}
