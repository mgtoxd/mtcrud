package pers.mtx.mt.crud;

import pers.mtx.connect.DataSourceImpl;
import pers.mtx.connect.PoolConnection;
import pers.mtx.grpc.mtcrud.DelParams;
import pers.mtx.mt.Crud;
import pers.mtx.mt.data.sql.BeforeImageSql;
import pers.mtx.mt.data.sql.MtDelSql;
import pers.mtx.service.AtServiceImpl;
import pers.mtx.util.BeanMapper;
import pers.mtx.util.LogUtil;
import pers.mtx.util.ResultSetConvertUtil;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * AT事务模式删除
 */
public class AtDel implements Crud {

    @Override
    public String make(DelParams getParamsProt) {
        pers.mtx.mt.data.sql.entity.DelParams delParams = BeanMapper.map(getParamsProt, pers.mtx.mt.data.sql.entity.DelParams.class);
        List<Map<String, String>> beforeData = execBeforeSql(delParams);
        Long id = AtServiceImpl.getInstance().beforeImagePut(Objects.requireNonNull(beforeData),delParams.getDbName(), delParams.getTbName());
        execSql(delParams);
        List<Map<String, String>> afterData =execAfterSql(delParams);
        AtServiceImpl.getInstance().afterImagePut(id,afterData);
        return String.valueOf(id);
    }

    /**
     * 执行获取before image 的sql
     * @param delParams 删除参数
     * @return 数据
     */
    private List<Map<String,String>> execBeforeSql(pers.mtx.mt.data.sql.entity.DelParams delParams) {
         PoolConnection connection = DataSourceImpl.getConnection();
        try (Statement statement = connection.getConnect().createStatement()){
            String sql = BeforeImageSql.spliceSql(delParams);
            ResultSet resultSet = statement.executeQuery(sql);
            return ResultSetConvertUtil.convertListAndValueStr(resultSet);
        } catch (Exception e) {
            LogUtil.getExceptionInfo(e);
            return null;
        }
    }
    /**
     * 执行操作sql
     * @param delParams 删除参数
     */
    private void execSql(pers.mtx.mt.data.sql.entity.DelParams delParams){
        PoolConnection connection = DataSourceImpl.getConnection();
        try (Statement statement = connection.getConnect().createStatement()){
            String sql = MtDelSql.spliceSql(delParams);
            statement.executeUpdate(sql);
        } catch (Exception e) {
            LogUtil.getExceptionInfo(e);
        } finally {
            connection.releaseConnect();
        }
    }

    /**
     * 执行获取after image的sql
     * @param delParams 删除参数
     * @return 数据
     */
    private List<Map<String,String>> execAfterSql(pers.mtx.mt.data.sql.entity.DelParams delParams) {
        PoolConnection connection = DataSourceImpl.getConnection();
        try (Statement statement = connection.getConnect().createStatement()){
            String sql = BeforeImageSql.spliceSql(delParams);
            ResultSet resultSet = statement.executeQuery(sql);
            return ResultSetConvertUtil.convertListAndValueStr(resultSet);
        } catch (Exception e) {
            LogUtil.getExceptionInfo(e);
            return null;
        }
    }


}
