package pers.mtx.mt.crud;

import pers.mtx.connect.DataSourceImpl;
import pers.mtx.connect.PoolConnection;
import pers.mtx.mt.Crud;
import pers.mtx.mt.data.sql.MtGetSql;
import pers.mtx.mt.data.sql.entity.GetParams;
import pers.mtx.util.BeanMapper;
import pers.mtx.util.JsonUtil;
import pers.mtx.util.LogUtil;
import pers.mtx.util.ResultSetConvertUtil;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MGet implements Crud {
    @Override
    public String make(String uri, byte[] content) {
        //解析content
        GetParams value = new GetParams();
        //解析uri
        String[] split = uri.replaceFirst("/mget/", "").split("/");
        value.setDbName(split[0]);
        value.setTbName(split[1]);
        List<String> cols = Arrays.stream(split[2].split(";")).collect(Collectors.toList());

        value.setCols(cols);


        return execSql(value);

    }

    @Override
    public List<Map<String,String>> make(pers.mtx.grpc.mtcrud.GetParams getParamsProt) {
        GetParams params = BeanMapper.map(getParamsProt, GetParams.class);
        return execSqlObj(params);
    }

    /**
     * 执行复杂查询操作sql，返回JSON
     * @param value 查询参数
     * @return 数据的JSON字符串
     */
    public String execSql(GetParams value){
        PoolConnection connection = DataSourceImpl.getConnection();
        try (Statement statement = connection.getConnect().createStatement()){
            String sql = MtGetSql.spliceSql(value);
            ResultSet resultSet = statement.executeQuery(sql);
            return JsonUtil.resultSetToJson(resultSet);
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            connection.releaseConnect();
        }
    }

    /**
     * 执行复杂查询操作sql，返回OBJ
     * @param value 查询参数
     * @return 数据
     */
    public List<Map<String,String>> execSqlObj(GetParams value){
        PoolConnection connection = DataSourceImpl.getConnection();
        try (Statement statement = connection.getConnect().createStatement()) {
            String sql = MtGetSql.spliceSql(value);
            ResultSet resultSet = statement.executeQuery(sql);
            return ResultSetConvertUtil.convertListAndValueStr(resultSet);
        } catch (Exception e) {
            System.out.println(LogUtil.getExceptionInfo(e));
            return null;
        } finally {
            connection.releaseConnect();
        }
    }


}
