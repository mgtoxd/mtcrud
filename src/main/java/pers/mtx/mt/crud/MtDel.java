package pers.mtx.mt.crud;

import com.fasterxml.jackson.databind.ObjectMapper;
import pers.mtx.connect.DataSourceImpl;
import pers.mtx.connect.PoolConnection;
import pers.mtx.mt.Crud;
import pers.mtx.mt.data.sql.MtDelSql;
import pers.mtx.mt.data.sql.entity.DelParams;
import pers.mtx.util.BeanMapper;
import pers.mtx.util.LogUtil;

import java.io.IOException;
import java.sql.Statement;

public class MtDel implements Crud {
    @Override
    public String make(String uri,byte[] content) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String[] split = uri.replaceFirst("/mt/","").split("/");
        DelParams delParams = mapper.readValue(content, DelParams.class);
        delParams.setDbName(split[0]);
        delParams.setTbName(split[1]);

        return execSql(delParams);
    }

    @Override
    public String make(pers.mtx.grpc.mtcrud.DelParams getParamsProt) {
        DelParams params = BeanMapper.map(getParamsProt, DelParams.class);
        try {
            return execSql(params);
        }catch (Exception e){
            LogUtil.getExceptionInfo(e);
        }
        return "f";
    }

    /**
     * 执行删除操作sql
     * @param delParams 删除参数
     * @return t或者f
     */
    private String execSql(DelParams delParams) {
        String sql = MtDelSql.spliceSql(delParams);
        PoolConnection poolConnection = DataSourceImpl.getConnection();
        try (Statement statement = poolConnection.getConnect().createStatement()) {
            statement.executeUpdate(sql);
            return "t";
        }catch (Exception e){
            //System.out.println(e);
            return "f";
        }finally {
            poolConnection.releaseConnect();
        }
    }

}
