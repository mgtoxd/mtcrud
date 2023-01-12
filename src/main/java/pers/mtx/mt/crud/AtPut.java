package pers.mtx.mt.crud;

import pers.mtx.connect.DataSourceImpl;
import pers.mtx.connect.PoolConnection;
import pers.mtx.grpc.mtcrud.PutParams;
import pers.mtx.mt.Crud;
import pers.mtx.mt.data.sql.BeforeImageSql;
import pers.mtx.mt.data.sql.MtPutSql;
import pers.mtx.service.AtServiceImpl;
import pers.mtx.util.BeanMapper;
import pers.mtx.util.LogUtil;
import pers.mtx.util.ResultSetConvertUtil;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class AtPut implements Crud {

    @Override
    public String make(PutParams getParamsProt) {
        pers.mtx.mt.data.sql.entity.PutParams putParams = BeanMapper.map(getParamsProt, pers.mtx.mt.data.sql.entity.PutParams.class);
        List<Map<String, String>> beforeData = execBeforeSql(putParams);
        Long id = AtServiceImpl.getInstance().beforeImagePut(Objects.requireNonNull(beforeData), putParams.getDbName(), putParams.getTbName());
        execSql(putParams);
        List<Map<String, String>> afterData =execAfterSql(putParams);
        AtServiceImpl.getInstance().afterImagePut(id,afterData);
        return String.valueOf(id);
    }

    private List<Map<String,String>> execBeforeSql(pers.mtx.mt.data.sql.entity.PutParams putParams) {
         PoolConnection connection = DataSourceImpl.getConnection();
        try (Statement statement = connection.getConnect().createStatement()){
            String sql = BeforeImageSql.spliceSql(putParams);
            ResultSet resultSet = statement.executeQuery(sql);
            return ResultSetConvertUtil.convertListAndValueStr(resultSet);
        } catch (Exception e) {
            LogUtil.getExceptionInfo(e);
            return null;
        }
    }

    private void execSql(pers.mtx.mt.data.sql.entity.PutParams putParams){
        PoolConnection connection = DataSourceImpl.getConnection();
        try (Statement statement = connection.getConnect().createStatement()){
            String sql = MtPutSql.spliceSql(putParams);
            statement.executeUpdate(sql);
        } catch (Exception e) {
            LogUtil.getExceptionInfo(e);
        } finally {
            connection.releaseConnect();
        }
    }

    private List<Map<String,String>> execAfterSql(pers.mtx.mt.data.sql.entity.PutParams putParams) {
        PoolConnection connection = DataSourceImpl.getConnection();
        try (Statement statement = connection.getConnect().createStatement()){
            String sql = BeforeImageSql.spliceSql(putParams);
            ResultSet resultSet = statement.executeQuery(sql);
            return ResultSetConvertUtil.convertListAndValueStr(resultSet);
        } catch (Exception e) {
            LogUtil.getExceptionInfo(e);
            return null;
        }
    }


}
