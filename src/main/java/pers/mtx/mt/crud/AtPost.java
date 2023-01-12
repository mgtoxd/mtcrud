package pers.mtx.mt.crud;

import pers.mtx.connect.DataSourceImpl;
import pers.mtx.connect.PoolConnection;
import pers.mtx.grpc.mtcrud.PostParams;
import pers.mtx.mt.Crud;
import pers.mtx.mt.data.sql.BeforeImageSql;
import pers.mtx.mt.data.sql.MtPostSql;
import pers.mtx.service.AtServiceImpl;
import pers.mtx.util.BeanMapper;
import pers.mtx.util.IdWorker;
import pers.mtx.util.LogUtil;
import pers.mtx.util.ResultSetConvertUtil;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * AT 事务模式增加
 */
public class AtPost implements Crud {

    @Override
    public String make(PostParams getParamsProt) {
        pers.mtx.mt.data.sql.entity.PostParams postParams = BeanMapper.map(getParamsProt, pers.mtx.mt.data.sql.entity.PostParams.class);
        List<Map<String, String>> beforeData = execBeforeSql(postParams);
        Long affairId = AtServiceImpl.getInstance().beforeImagePut(beforeData,postParams.getDbName(), postParams.getTbName());
        Long rowId = execSql(postParams);
        List<Map<String, String>> afterData =execAfterSql(postParams,rowId);
        AtServiceImpl.getInstance().afterImagePut(affairId,afterData);
        return String.valueOf(affairId);
    }

    /**
     * 执行获取before image 的sql post为新增，before为空
     * @param ignoredPostParams 忽视的增加参数
     * @return 空数据
     */
    private List<Map<String,String>> execBeforeSql(pers.mtx.mt.data.sql.entity.PostParams ignoredPostParams) {
        return new ArrayList<>();
    }

    /**
     * 执行插入，如果没有指定id说明为数据库生成的自增id，返回id
     * @param postParams 增加参数
     * @return 数据库数据id
     */
    //
    private Long execSql(pers.mtx.mt.data.sql.entity.PostParams postParams){
        PoolConnection connection = DataSourceImpl.getConnection();
        try (Statement statement = connection.getConnect().createStatement()){
            String sql;
            if (postParams.getId().isEmpty()){
                sql = MtPostSql.spliceSql(postParams);
                int i = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                return new Integer(i).longValue();
            }else {
                long id = IdWorker.idWorker.nextId();
                sql = MtPostSql.spliceSqlWithId(postParams, String.valueOf(id));
                statement.executeUpdate(sql);
                return id;
            }


        } catch (Exception e) {
            LogUtil.getExceptionInfo(e);
            return null;
        } finally {
            connection.releaseConnect();
        }
    }


    /**
     * 执行获取after image的sql，需要rowid
     * @param postParams 增加参数
     * @param rowId 行id
     * @return 数据
     */
    private List<Map<String,String>> execAfterSql(pers.mtx.mt.data.sql.entity.PostParams postParams,Long rowId) {
        PoolConnection connection = DataSourceImpl.getConnection();
        try (Statement statement = connection.getConnect().createStatement()){
            String sql = BeforeImageSql.spliceSql(postParams,rowId);
            ResultSet resultSet = statement.executeQuery(sql);
            return ResultSetConvertUtil.convertListAndValueStr(resultSet);
        } catch (Exception e) {
            LogUtil.getExceptionInfo(e);
            return null;
        }
    }


}
