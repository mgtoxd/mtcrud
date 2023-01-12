package pers.mtx.service;

import io.grpc.stub.StreamObserver;
import pers.mtx.grpc.mtcrud.*;
import pers.mtx.mt.Crud;
import pers.mtx.mt.CrudFactory;
import pers.mtx.util.LogUtil;

import java.util.List;
import java.util.Map;

public class MtCrudGrpcServiceImpl extends MtCrudGrpc.MtCrudImplBase {

    /**
     * 查询
     */
    @Override
    public void getData(GetParams request, StreamObserver<Data> responseObserver) {
        Crud mget = CrudFactory.getCrudByName("mget");
//        mget.make(request);
        try {
            List<Map<String, String>> make = mget.make(request);
            make.forEach((t) -> responseObserver.onNext(Data.newBuilder().putAllDataMap(t).build()));
            responseObserver.onCompleted();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 新增
     */
    @Override
    public void postData(PostParams request, StreamObserver<Info> responseObserver) {
        Crud crud = CrudFactory.getCrudByName("POST");
        try {
            Info info = Info.newBuilder().setInfo(crud.make(request)).build();
            responseObserver.onNext(info);
            responseObserver.onCompleted();
        } catch (Exception e) {
            LogUtil.getExceptionInfo(e);
        }
    }

    /**
     * 更新
     */
    @Override
    public void putData(PutParams request, StreamObserver<Info> responseObserver) {
        Crud crud = CrudFactory.getCrudByName("PUT");
        try {
            Info info = Info.newBuilder().setInfo(crud.make(request)).build();
            responseObserver.onNext(info);
            responseObserver.onCompleted();
        } catch (Exception e) {
            LogUtil.getExceptionInfo(e);
        }
    }

    /**
     * 删除
     */
    @Override
    public void delData(DelParams request, StreamObserver<Info> responseObserver) {
        Crud crud = CrudFactory.getCrudByName("DEL");
        try {
            Info info = Info.newBuilder().setInfo(crud.make(request)).build();
            responseObserver.onNext(info);
            responseObserver.onCompleted();
        } catch (Exception e) {
            LogUtil.getExceptionInfo(e);
        }
    }

    /**
     * AT 事务删除
     */
    @Override
    public void aTDelData(DelParams request, StreamObserver<ATTransactionId> responseObserver) {
//        获取现有数据
        Crud atDel = CrudFactory.getCrudByName("AtDEL");
        try {
            ATTransactionId info = ATTransactionId.newBuilder().setATId(Long.parseLong(atDel.make(request))).build();
            responseObserver.onNext(info);
            responseObserver.onCompleted();
        } catch (Exception e) {
            LogUtil.getExceptionInfo(e);
        }
    }

    /**
     * AT 事务增加
     */
    @Override
    public void aTPostData(PostParams request, StreamObserver<ATTransactionId> responseObserver) {
        Crud atPost = CrudFactory.getCrudByName("AtPost");
        try {
            ATTransactionId info = ATTransactionId.newBuilder().setATId(Long.parseLong(atPost.make(request))).build();
            responseObserver.onNext(info);
            responseObserver.onCompleted();
        } catch (Exception e) {
            LogUtil.getExceptionInfo(e);
        }
    }

    /**
     * AT 事务更新
     */
    @Override
    public void aTPutData(PutParams request, StreamObserver<ATTransactionId> responseObserver) {
        Crud atPut = CrudFactory.getCrudByName("AtPut");
        try {
            ATTransactionId info = ATTransactionId.newBuilder().setATId(Long.parseLong(atPut.make(request))).build();
            responseObserver.onNext(info);
            responseObserver.onCompleted();
        } catch (Exception e) {
            LogUtil.getExceptionInfo(e);
        }
    }

    /**
     * AT 确认
     */
    @Override
    public void confirmAT(ATTransactionId request, StreamObserver<Info> responseObserver) {
        if (AtServiceImpl.getInstance().confirm(request.getATId())){
            responseObserver.onNext(Info.newBuilder().setInfo("confirm success").build());
        }else {
            responseObserver.onNext(Info.newBuilder().setInfo("confirm fail").build());
        }
        responseObserver.onCompleted();
    }

    /**
     * AT 回滚
     */
    @Override
    public void rollbackAT(ATTransactionId request, StreamObserver<Info> responseObserver) {
        if (AtServiceImpl.getInstance().rollback(request.getATId())){
            responseObserver.onNext(Info.newBuilder().setInfo("rollback success").build());

        }else {
            responseObserver.onNext(Info.newBuilder().setInfo("rollback fail").build());

        }
        responseObserver.onCompleted();
    }
}
