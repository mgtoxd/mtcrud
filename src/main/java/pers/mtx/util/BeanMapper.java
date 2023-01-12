package pers.mtx.util;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;
import pers.mtx.mt.data.sql.entity.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 对orika进行简单的封装
 */
public class BeanMapper {
    private static final MapperFacade mapperFacade;


    static {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.getConverterFactory().registerConverter(new GetParamsConverter());
        mapperFactory.getConverterFactory().registerConverter(new PutParamsConverter());
        mapperFactory.getConverterFactory().registerConverter(new PostParamsConverter());
        mapperFactory.getConverterFactory().registerConverter(new ConditionConverter());
        mapperFactory.getConverterFactory().registerConverter(new DelParamsConverter());
        mapperFacade = mapperFactory.getMapperFacade();

    }

    private static class GetParamsConverter extends CustomConverter<pers.mtx.grpc.mtcrud.GetParams, GetParams> {
        @Override
        public GetParams convert(pers.mtx.grpc.mtcrud.GetParams getParams, Type<? extends GetParams> type, MappingContext mappingContext) {
            GetParams rs = new GetParams();
            rs.setOrder(new HashMap<>(getParams.getOrderMap()))
                    .setLimit(getParams.getLimit())
                    .setCols(getParams.getColsList())
                    .setEq(new HashMap<>(getParams.getEqMap()))
                    .setGt(new HashMap<>(getParams.getGtMap()))
                    .setLt(new HashMap<>(getParams.getLtMap()))
                    .setLike(new HashMap<>(getParams.getLikeMap()))
                    .setTbName(getParams.getTbName())
                    .setDbName(getParams.getDbName());
            return rs;
        }

    }

    private static class PostParamsConverter extends CustomConverter<pers.mtx.grpc.mtcrud.PostParams, PostParams> {
        @Override
        public PostParams convert(pers.mtx.grpc.mtcrud.PostParams postParams, Type<? extends PostParams> type, MappingContext mappingContext) {
            PostParams rs = new PostParams();

            rs.setValueMap(new ArrayList<>(postParams.getValueMapMap().entrySet()))
                    .setId(postParams.getId())
                    .setCt(postParams.getCt())
                    .setUt(postParams.getUt())
                    .setTbName(postParams.getTbName())
                    .setDbName(postParams.getDbName());
            return rs;
        }
    }

    private static class PutParamsConverter extends CustomConverter<pers.mtx.grpc.mtcrud.PutParams, PutParams> {
        @Override
        public PutParams convert(pers.mtx.grpc.mtcrud.PutParams putParams, Type<? extends PutParams> type, MappingContext mappingContext) {
            PutParams rs = new PutParams();
            rs.setValueMap(new ArrayList<>(putParams.getValueMapMap().entrySet()))
                    .setCondition(BeanMapper.map(putParams.getCondition(), Condition.class))
                    .setDbName(putParams.getDbName())
                    .setTbName(putParams.getTbName());
            return rs;
        }
    }

    private static class ConditionConverter extends CustomConverter<pers.mtx.grpc.mtcrud.Condition, Condition> {

        @Override
        public Condition convert(pers.mtx.grpc.mtcrud.Condition condition, Type<? extends Condition> type, MappingContext mappingContext) {
            Condition condition1 = new Condition();
            condition1.setEq(new HashMap<>(condition.getEqMap()))
                    .setGt(new HashMap<>(condition.getGtMap()))
                    .setLt(new HashMap<>(condition.getLtMap()))
                    .setLike(new HashMap<>(condition.getLikeMap()));
            return condition1;
        }
    }

    private static class DelParamsConverter extends CustomConverter<pers.mtx.grpc.mtcrud.DelParams, DelParams> {
        @Override
        public DelParams convert(pers.mtx.grpc.mtcrud.DelParams delParams, Type<? extends DelParams> type, MappingContext mappingContext) {
            DelParams rs = new DelParams();
            rs.setEq(new HashMap<>(delParams.getEqMap()))
                    .setGt(new HashMap<>(delParams.getGtMap()))
                    .setLt(new HashMap<>(delParams.getLtMap()))
                    .setLike(new HashMap<>(delParams.getLikeMap()))
                    .setDbName(delParams.getDbName())
                    .setTbName(delParams.getTbName());

            return rs;
        }
    }

    public static <S, T> T map(S sourceObj, Class<T> targetClass) {
        return mapperFacade.map(sourceObj, targetClass);
    }

}
