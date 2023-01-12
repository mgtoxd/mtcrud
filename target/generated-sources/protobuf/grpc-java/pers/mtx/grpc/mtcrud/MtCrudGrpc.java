package pers.mtx.grpc.mtcrud;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.51.0)",
    comments = "Source: mtcrud.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MtCrudGrpc {

  private MtCrudGrpc() {}

  public static final String SERVICE_NAME = "mtCrud.MtCrud";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<pers.mtx.grpc.mtcrud.GetParams,
      pers.mtx.grpc.mtcrud.Data> getGetDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetData",
      requestType = pers.mtx.grpc.mtcrud.GetParams.class,
      responseType = pers.mtx.grpc.mtcrud.Data.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<pers.mtx.grpc.mtcrud.GetParams,
      pers.mtx.grpc.mtcrud.Data> getGetDataMethod() {
    io.grpc.MethodDescriptor<pers.mtx.grpc.mtcrud.GetParams, pers.mtx.grpc.mtcrud.Data> getGetDataMethod;
    if ((getGetDataMethod = MtCrudGrpc.getGetDataMethod) == null) {
      synchronized (MtCrudGrpc.class) {
        if ((getGetDataMethod = MtCrudGrpc.getGetDataMethod) == null) {
          MtCrudGrpc.getGetDataMethod = getGetDataMethod =
              io.grpc.MethodDescriptor.<pers.mtx.grpc.mtcrud.GetParams, pers.mtx.grpc.mtcrud.Data>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pers.mtx.grpc.mtcrud.GetParams.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pers.mtx.grpc.mtcrud.Data.getDefaultInstance()))
              .setSchemaDescriptor(new MtCrudMethodDescriptorSupplier("GetData"))
              .build();
        }
      }
    }
    return getGetDataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pers.mtx.grpc.mtcrud.PostParams,
      pers.mtx.grpc.mtcrud.Info> getPostDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PostData",
      requestType = pers.mtx.grpc.mtcrud.PostParams.class,
      responseType = pers.mtx.grpc.mtcrud.Info.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pers.mtx.grpc.mtcrud.PostParams,
      pers.mtx.grpc.mtcrud.Info> getPostDataMethod() {
    io.grpc.MethodDescriptor<pers.mtx.grpc.mtcrud.PostParams, pers.mtx.grpc.mtcrud.Info> getPostDataMethod;
    if ((getPostDataMethod = MtCrudGrpc.getPostDataMethod) == null) {
      synchronized (MtCrudGrpc.class) {
        if ((getPostDataMethod = MtCrudGrpc.getPostDataMethod) == null) {
          MtCrudGrpc.getPostDataMethod = getPostDataMethod =
              io.grpc.MethodDescriptor.<pers.mtx.grpc.mtcrud.PostParams, pers.mtx.grpc.mtcrud.Info>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PostData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pers.mtx.grpc.mtcrud.PostParams.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pers.mtx.grpc.mtcrud.Info.getDefaultInstance()))
              .setSchemaDescriptor(new MtCrudMethodDescriptorSupplier("PostData"))
              .build();
        }
      }
    }
    return getPostDataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pers.mtx.grpc.mtcrud.PutParams,
      pers.mtx.grpc.mtcrud.Info> getPutDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PutData",
      requestType = pers.mtx.grpc.mtcrud.PutParams.class,
      responseType = pers.mtx.grpc.mtcrud.Info.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pers.mtx.grpc.mtcrud.PutParams,
      pers.mtx.grpc.mtcrud.Info> getPutDataMethod() {
    io.grpc.MethodDescriptor<pers.mtx.grpc.mtcrud.PutParams, pers.mtx.grpc.mtcrud.Info> getPutDataMethod;
    if ((getPutDataMethod = MtCrudGrpc.getPutDataMethod) == null) {
      synchronized (MtCrudGrpc.class) {
        if ((getPutDataMethod = MtCrudGrpc.getPutDataMethod) == null) {
          MtCrudGrpc.getPutDataMethod = getPutDataMethod =
              io.grpc.MethodDescriptor.<pers.mtx.grpc.mtcrud.PutParams, pers.mtx.grpc.mtcrud.Info>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PutData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pers.mtx.grpc.mtcrud.PutParams.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pers.mtx.grpc.mtcrud.Info.getDefaultInstance()))
              .setSchemaDescriptor(new MtCrudMethodDescriptorSupplier("PutData"))
              .build();
        }
      }
    }
    return getPutDataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pers.mtx.grpc.mtcrud.DelParams,
      pers.mtx.grpc.mtcrud.Info> getDelDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DelData",
      requestType = pers.mtx.grpc.mtcrud.DelParams.class,
      responseType = pers.mtx.grpc.mtcrud.Info.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pers.mtx.grpc.mtcrud.DelParams,
      pers.mtx.grpc.mtcrud.Info> getDelDataMethod() {
    io.grpc.MethodDescriptor<pers.mtx.grpc.mtcrud.DelParams, pers.mtx.grpc.mtcrud.Info> getDelDataMethod;
    if ((getDelDataMethod = MtCrudGrpc.getDelDataMethod) == null) {
      synchronized (MtCrudGrpc.class) {
        if ((getDelDataMethod = MtCrudGrpc.getDelDataMethod) == null) {
          MtCrudGrpc.getDelDataMethod = getDelDataMethod =
              io.grpc.MethodDescriptor.<pers.mtx.grpc.mtcrud.DelParams, pers.mtx.grpc.mtcrud.Info>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DelData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pers.mtx.grpc.mtcrud.DelParams.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pers.mtx.grpc.mtcrud.Info.getDefaultInstance()))
              .setSchemaDescriptor(new MtCrudMethodDescriptorSupplier("DelData"))
              .build();
        }
      }
    }
    return getDelDataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pers.mtx.grpc.mtcrud.DelParams,
      pers.mtx.grpc.mtcrud.ATTransactionId> getATDelDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ATDelData",
      requestType = pers.mtx.grpc.mtcrud.DelParams.class,
      responseType = pers.mtx.grpc.mtcrud.ATTransactionId.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pers.mtx.grpc.mtcrud.DelParams,
      pers.mtx.grpc.mtcrud.ATTransactionId> getATDelDataMethod() {
    io.grpc.MethodDescriptor<pers.mtx.grpc.mtcrud.DelParams, pers.mtx.grpc.mtcrud.ATTransactionId> getATDelDataMethod;
    if ((getATDelDataMethod = MtCrudGrpc.getATDelDataMethod) == null) {
      synchronized (MtCrudGrpc.class) {
        if ((getATDelDataMethod = MtCrudGrpc.getATDelDataMethod) == null) {
          MtCrudGrpc.getATDelDataMethod = getATDelDataMethod =
              io.grpc.MethodDescriptor.<pers.mtx.grpc.mtcrud.DelParams, pers.mtx.grpc.mtcrud.ATTransactionId>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ATDelData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pers.mtx.grpc.mtcrud.DelParams.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pers.mtx.grpc.mtcrud.ATTransactionId.getDefaultInstance()))
              .setSchemaDescriptor(new MtCrudMethodDescriptorSupplier("ATDelData"))
              .build();
        }
      }
    }
    return getATDelDataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pers.mtx.grpc.mtcrud.PostParams,
      pers.mtx.grpc.mtcrud.ATTransactionId> getATPostDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ATPostData",
      requestType = pers.mtx.grpc.mtcrud.PostParams.class,
      responseType = pers.mtx.grpc.mtcrud.ATTransactionId.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pers.mtx.grpc.mtcrud.PostParams,
      pers.mtx.grpc.mtcrud.ATTransactionId> getATPostDataMethod() {
    io.grpc.MethodDescriptor<pers.mtx.grpc.mtcrud.PostParams, pers.mtx.grpc.mtcrud.ATTransactionId> getATPostDataMethod;
    if ((getATPostDataMethod = MtCrudGrpc.getATPostDataMethod) == null) {
      synchronized (MtCrudGrpc.class) {
        if ((getATPostDataMethod = MtCrudGrpc.getATPostDataMethod) == null) {
          MtCrudGrpc.getATPostDataMethod = getATPostDataMethod =
              io.grpc.MethodDescriptor.<pers.mtx.grpc.mtcrud.PostParams, pers.mtx.grpc.mtcrud.ATTransactionId>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ATPostData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pers.mtx.grpc.mtcrud.PostParams.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pers.mtx.grpc.mtcrud.ATTransactionId.getDefaultInstance()))
              .setSchemaDescriptor(new MtCrudMethodDescriptorSupplier("ATPostData"))
              .build();
        }
      }
    }
    return getATPostDataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pers.mtx.grpc.mtcrud.PutParams,
      pers.mtx.grpc.mtcrud.ATTransactionId> getATPutDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ATPutData",
      requestType = pers.mtx.grpc.mtcrud.PutParams.class,
      responseType = pers.mtx.grpc.mtcrud.ATTransactionId.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pers.mtx.grpc.mtcrud.PutParams,
      pers.mtx.grpc.mtcrud.ATTransactionId> getATPutDataMethod() {
    io.grpc.MethodDescriptor<pers.mtx.grpc.mtcrud.PutParams, pers.mtx.grpc.mtcrud.ATTransactionId> getATPutDataMethod;
    if ((getATPutDataMethod = MtCrudGrpc.getATPutDataMethod) == null) {
      synchronized (MtCrudGrpc.class) {
        if ((getATPutDataMethod = MtCrudGrpc.getATPutDataMethod) == null) {
          MtCrudGrpc.getATPutDataMethod = getATPutDataMethod =
              io.grpc.MethodDescriptor.<pers.mtx.grpc.mtcrud.PutParams, pers.mtx.grpc.mtcrud.ATTransactionId>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ATPutData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pers.mtx.grpc.mtcrud.PutParams.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pers.mtx.grpc.mtcrud.ATTransactionId.getDefaultInstance()))
              .setSchemaDescriptor(new MtCrudMethodDescriptorSupplier("ATPutData"))
              .build();
        }
      }
    }
    return getATPutDataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pers.mtx.grpc.mtcrud.ATTransactionId,
      pers.mtx.grpc.mtcrud.Info> getConfirmATMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConfirmAT",
      requestType = pers.mtx.grpc.mtcrud.ATTransactionId.class,
      responseType = pers.mtx.grpc.mtcrud.Info.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pers.mtx.grpc.mtcrud.ATTransactionId,
      pers.mtx.grpc.mtcrud.Info> getConfirmATMethod() {
    io.grpc.MethodDescriptor<pers.mtx.grpc.mtcrud.ATTransactionId, pers.mtx.grpc.mtcrud.Info> getConfirmATMethod;
    if ((getConfirmATMethod = MtCrudGrpc.getConfirmATMethod) == null) {
      synchronized (MtCrudGrpc.class) {
        if ((getConfirmATMethod = MtCrudGrpc.getConfirmATMethod) == null) {
          MtCrudGrpc.getConfirmATMethod = getConfirmATMethod =
              io.grpc.MethodDescriptor.<pers.mtx.grpc.mtcrud.ATTransactionId, pers.mtx.grpc.mtcrud.Info>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConfirmAT"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pers.mtx.grpc.mtcrud.ATTransactionId.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pers.mtx.grpc.mtcrud.Info.getDefaultInstance()))
              .setSchemaDescriptor(new MtCrudMethodDescriptorSupplier("ConfirmAT"))
              .build();
        }
      }
    }
    return getConfirmATMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pers.mtx.grpc.mtcrud.ATTransactionId,
      pers.mtx.grpc.mtcrud.Info> getRollbackATMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RollbackAT",
      requestType = pers.mtx.grpc.mtcrud.ATTransactionId.class,
      responseType = pers.mtx.grpc.mtcrud.Info.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pers.mtx.grpc.mtcrud.ATTransactionId,
      pers.mtx.grpc.mtcrud.Info> getRollbackATMethod() {
    io.grpc.MethodDescriptor<pers.mtx.grpc.mtcrud.ATTransactionId, pers.mtx.grpc.mtcrud.Info> getRollbackATMethod;
    if ((getRollbackATMethod = MtCrudGrpc.getRollbackATMethod) == null) {
      synchronized (MtCrudGrpc.class) {
        if ((getRollbackATMethod = MtCrudGrpc.getRollbackATMethod) == null) {
          MtCrudGrpc.getRollbackATMethod = getRollbackATMethod =
              io.grpc.MethodDescriptor.<pers.mtx.grpc.mtcrud.ATTransactionId, pers.mtx.grpc.mtcrud.Info>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RollbackAT"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pers.mtx.grpc.mtcrud.ATTransactionId.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pers.mtx.grpc.mtcrud.Info.getDefaultInstance()))
              .setSchemaDescriptor(new MtCrudMethodDescriptorSupplier("RollbackAT"))
              .build();
        }
      }
    }
    return getRollbackATMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MtCrudStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MtCrudStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MtCrudStub>() {
        @java.lang.Override
        public MtCrudStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MtCrudStub(channel, callOptions);
        }
      };
    return MtCrudStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MtCrudBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MtCrudBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MtCrudBlockingStub>() {
        @java.lang.Override
        public MtCrudBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MtCrudBlockingStub(channel, callOptions);
        }
      };
    return MtCrudBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MtCrudFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MtCrudFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MtCrudFutureStub>() {
        @java.lang.Override
        public MtCrudFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MtCrudFutureStub(channel, callOptions);
        }
      };
    return MtCrudFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class MtCrudImplBase implements io.grpc.BindableService {

    /**
     */
    public void getData(pers.mtx.grpc.mtcrud.GetParams request,
        io.grpc.stub.StreamObserver<pers.mtx.grpc.mtcrud.Data> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetDataMethod(), responseObserver);
    }

    /**
     */
    public void postData(pers.mtx.grpc.mtcrud.PostParams request,
        io.grpc.stub.StreamObserver<pers.mtx.grpc.mtcrud.Info> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPostDataMethod(), responseObserver);
    }

    /**
     */
    public void putData(pers.mtx.grpc.mtcrud.PutParams request,
        io.grpc.stub.StreamObserver<pers.mtx.grpc.mtcrud.Info> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPutDataMethod(), responseObserver);
    }

    /**
     */
    public void delData(pers.mtx.grpc.mtcrud.DelParams request,
        io.grpc.stub.StreamObserver<pers.mtx.grpc.mtcrud.Info> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDelDataMethod(), responseObserver);
    }

    /**
     */
    public void aTDelData(pers.mtx.grpc.mtcrud.DelParams request,
        io.grpc.stub.StreamObserver<pers.mtx.grpc.mtcrud.ATTransactionId> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getATDelDataMethod(), responseObserver);
    }

    /**
     */
    public void aTPostData(pers.mtx.grpc.mtcrud.PostParams request,
        io.grpc.stub.StreamObserver<pers.mtx.grpc.mtcrud.ATTransactionId> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getATPostDataMethod(), responseObserver);
    }

    /**
     */
    public void aTPutData(pers.mtx.grpc.mtcrud.PutParams request,
        io.grpc.stub.StreamObserver<pers.mtx.grpc.mtcrud.ATTransactionId> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getATPutDataMethod(), responseObserver);
    }

    /**
     */
    public void confirmAT(pers.mtx.grpc.mtcrud.ATTransactionId request,
        io.grpc.stub.StreamObserver<pers.mtx.grpc.mtcrud.Info> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getConfirmATMethod(), responseObserver);
    }

    /**
     */
    public void rollbackAT(pers.mtx.grpc.mtcrud.ATTransactionId request,
        io.grpc.stub.StreamObserver<pers.mtx.grpc.mtcrud.Info> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRollbackATMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetDataMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                pers.mtx.grpc.mtcrud.GetParams,
                pers.mtx.grpc.mtcrud.Data>(
                  this, METHODID_GET_DATA)))
          .addMethod(
            getPostDataMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                pers.mtx.grpc.mtcrud.PostParams,
                pers.mtx.grpc.mtcrud.Info>(
                  this, METHODID_POST_DATA)))
          .addMethod(
            getPutDataMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                pers.mtx.grpc.mtcrud.PutParams,
                pers.mtx.grpc.mtcrud.Info>(
                  this, METHODID_PUT_DATA)))
          .addMethod(
            getDelDataMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                pers.mtx.grpc.mtcrud.DelParams,
                pers.mtx.grpc.mtcrud.Info>(
                  this, METHODID_DEL_DATA)))
          .addMethod(
            getATDelDataMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                pers.mtx.grpc.mtcrud.DelParams,
                pers.mtx.grpc.mtcrud.ATTransactionId>(
                  this, METHODID_ATDEL_DATA)))
          .addMethod(
            getATPostDataMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                pers.mtx.grpc.mtcrud.PostParams,
                pers.mtx.grpc.mtcrud.ATTransactionId>(
                  this, METHODID_ATPOST_DATA)))
          .addMethod(
            getATPutDataMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                pers.mtx.grpc.mtcrud.PutParams,
                pers.mtx.grpc.mtcrud.ATTransactionId>(
                  this, METHODID_ATPUT_DATA)))
          .addMethod(
            getConfirmATMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                pers.mtx.grpc.mtcrud.ATTransactionId,
                pers.mtx.grpc.mtcrud.Info>(
                  this, METHODID_CONFIRM_AT)))
          .addMethod(
            getRollbackATMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                pers.mtx.grpc.mtcrud.ATTransactionId,
                pers.mtx.grpc.mtcrud.Info>(
                  this, METHODID_ROLLBACK_AT)))
          .build();
    }
  }

  /**
   */
  public static final class MtCrudStub extends io.grpc.stub.AbstractAsyncStub<MtCrudStub> {
    private MtCrudStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MtCrudStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MtCrudStub(channel, callOptions);
    }

    /**
     */
    public void getData(pers.mtx.grpc.mtcrud.GetParams request,
        io.grpc.stub.StreamObserver<pers.mtx.grpc.mtcrud.Data> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getGetDataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void postData(pers.mtx.grpc.mtcrud.PostParams request,
        io.grpc.stub.StreamObserver<pers.mtx.grpc.mtcrud.Info> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPostDataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void putData(pers.mtx.grpc.mtcrud.PutParams request,
        io.grpc.stub.StreamObserver<pers.mtx.grpc.mtcrud.Info> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPutDataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void delData(pers.mtx.grpc.mtcrud.DelParams request,
        io.grpc.stub.StreamObserver<pers.mtx.grpc.mtcrud.Info> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDelDataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void aTDelData(pers.mtx.grpc.mtcrud.DelParams request,
        io.grpc.stub.StreamObserver<pers.mtx.grpc.mtcrud.ATTransactionId> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getATDelDataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void aTPostData(pers.mtx.grpc.mtcrud.PostParams request,
        io.grpc.stub.StreamObserver<pers.mtx.grpc.mtcrud.ATTransactionId> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getATPostDataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void aTPutData(pers.mtx.grpc.mtcrud.PutParams request,
        io.grpc.stub.StreamObserver<pers.mtx.grpc.mtcrud.ATTransactionId> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getATPutDataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void confirmAT(pers.mtx.grpc.mtcrud.ATTransactionId request,
        io.grpc.stub.StreamObserver<pers.mtx.grpc.mtcrud.Info> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getConfirmATMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void rollbackAT(pers.mtx.grpc.mtcrud.ATTransactionId request,
        io.grpc.stub.StreamObserver<pers.mtx.grpc.mtcrud.Info> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRollbackATMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class MtCrudBlockingStub extends io.grpc.stub.AbstractBlockingStub<MtCrudBlockingStub> {
    private MtCrudBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MtCrudBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MtCrudBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<pers.mtx.grpc.mtcrud.Data> getData(
        pers.mtx.grpc.mtcrud.GetParams request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getGetDataMethod(), getCallOptions(), request);
    }

    /**
     */
    public pers.mtx.grpc.mtcrud.Info postData(pers.mtx.grpc.mtcrud.PostParams request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPostDataMethod(), getCallOptions(), request);
    }

    /**
     */
    public pers.mtx.grpc.mtcrud.Info putData(pers.mtx.grpc.mtcrud.PutParams request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPutDataMethod(), getCallOptions(), request);
    }

    /**
     */
    public pers.mtx.grpc.mtcrud.Info delData(pers.mtx.grpc.mtcrud.DelParams request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDelDataMethod(), getCallOptions(), request);
    }

    /**
     */
    public pers.mtx.grpc.mtcrud.ATTransactionId aTDelData(pers.mtx.grpc.mtcrud.DelParams request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getATDelDataMethod(), getCallOptions(), request);
    }

    /**
     */
    public pers.mtx.grpc.mtcrud.ATTransactionId aTPostData(pers.mtx.grpc.mtcrud.PostParams request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getATPostDataMethod(), getCallOptions(), request);
    }

    /**
     */
    public pers.mtx.grpc.mtcrud.ATTransactionId aTPutData(pers.mtx.grpc.mtcrud.PutParams request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getATPutDataMethod(), getCallOptions(), request);
    }

    /**
     */
    public pers.mtx.grpc.mtcrud.Info confirmAT(pers.mtx.grpc.mtcrud.ATTransactionId request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConfirmATMethod(), getCallOptions(), request);
    }

    /**
     */
    public pers.mtx.grpc.mtcrud.Info rollbackAT(pers.mtx.grpc.mtcrud.ATTransactionId request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRollbackATMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class MtCrudFutureStub extends io.grpc.stub.AbstractFutureStub<MtCrudFutureStub> {
    private MtCrudFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MtCrudFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MtCrudFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pers.mtx.grpc.mtcrud.Info> postData(
        pers.mtx.grpc.mtcrud.PostParams request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPostDataMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pers.mtx.grpc.mtcrud.Info> putData(
        pers.mtx.grpc.mtcrud.PutParams request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPutDataMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pers.mtx.grpc.mtcrud.Info> delData(
        pers.mtx.grpc.mtcrud.DelParams request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDelDataMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pers.mtx.grpc.mtcrud.ATTransactionId> aTDelData(
        pers.mtx.grpc.mtcrud.DelParams request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getATDelDataMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pers.mtx.grpc.mtcrud.ATTransactionId> aTPostData(
        pers.mtx.grpc.mtcrud.PostParams request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getATPostDataMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pers.mtx.grpc.mtcrud.ATTransactionId> aTPutData(
        pers.mtx.grpc.mtcrud.PutParams request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getATPutDataMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pers.mtx.grpc.mtcrud.Info> confirmAT(
        pers.mtx.grpc.mtcrud.ATTransactionId request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getConfirmATMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pers.mtx.grpc.mtcrud.Info> rollbackAT(
        pers.mtx.grpc.mtcrud.ATTransactionId request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRollbackATMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_DATA = 0;
  private static final int METHODID_POST_DATA = 1;
  private static final int METHODID_PUT_DATA = 2;
  private static final int METHODID_DEL_DATA = 3;
  private static final int METHODID_ATDEL_DATA = 4;
  private static final int METHODID_ATPOST_DATA = 5;
  private static final int METHODID_ATPUT_DATA = 6;
  private static final int METHODID_CONFIRM_AT = 7;
  private static final int METHODID_ROLLBACK_AT = 8;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MtCrudImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MtCrudImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_DATA:
          serviceImpl.getData((pers.mtx.grpc.mtcrud.GetParams) request,
              (io.grpc.stub.StreamObserver<pers.mtx.grpc.mtcrud.Data>) responseObserver);
          break;
        case METHODID_POST_DATA:
          serviceImpl.postData((pers.mtx.grpc.mtcrud.PostParams) request,
              (io.grpc.stub.StreamObserver<pers.mtx.grpc.mtcrud.Info>) responseObserver);
          break;
        case METHODID_PUT_DATA:
          serviceImpl.putData((pers.mtx.grpc.mtcrud.PutParams) request,
              (io.grpc.stub.StreamObserver<pers.mtx.grpc.mtcrud.Info>) responseObserver);
          break;
        case METHODID_DEL_DATA:
          serviceImpl.delData((pers.mtx.grpc.mtcrud.DelParams) request,
              (io.grpc.stub.StreamObserver<pers.mtx.grpc.mtcrud.Info>) responseObserver);
          break;
        case METHODID_ATDEL_DATA:
          serviceImpl.aTDelData((pers.mtx.grpc.mtcrud.DelParams) request,
              (io.grpc.stub.StreamObserver<pers.mtx.grpc.mtcrud.ATTransactionId>) responseObserver);
          break;
        case METHODID_ATPOST_DATA:
          serviceImpl.aTPostData((pers.mtx.grpc.mtcrud.PostParams) request,
              (io.grpc.stub.StreamObserver<pers.mtx.grpc.mtcrud.ATTransactionId>) responseObserver);
          break;
        case METHODID_ATPUT_DATA:
          serviceImpl.aTPutData((pers.mtx.grpc.mtcrud.PutParams) request,
              (io.grpc.stub.StreamObserver<pers.mtx.grpc.mtcrud.ATTransactionId>) responseObserver);
          break;
        case METHODID_CONFIRM_AT:
          serviceImpl.confirmAT((pers.mtx.grpc.mtcrud.ATTransactionId) request,
              (io.grpc.stub.StreamObserver<pers.mtx.grpc.mtcrud.Info>) responseObserver);
          break;
        case METHODID_ROLLBACK_AT:
          serviceImpl.rollbackAT((pers.mtx.grpc.mtcrud.ATTransactionId) request,
              (io.grpc.stub.StreamObserver<pers.mtx.grpc.mtcrud.Info>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class MtCrudBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MtCrudBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return pers.mtx.grpc.mtcrud.mtcrudProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MtCrud");
    }
  }

  private static final class MtCrudFileDescriptorSupplier
      extends MtCrudBaseDescriptorSupplier {
    MtCrudFileDescriptorSupplier() {}
  }

  private static final class MtCrudMethodDescriptorSupplier
      extends MtCrudBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MtCrudMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MtCrudGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MtCrudFileDescriptorSupplier())
              .addMethod(getGetDataMethod())
              .addMethod(getPostDataMethod())
              .addMethod(getPutDataMethod())
              .addMethod(getDelDataMethod())
              .addMethod(getATDelDataMethod())
              .addMethod(getATPostDataMethod())
              .addMethod(getATPutDataMethod())
              .addMethod(getConfirmATMethod())
              .addMethod(getRollbackATMethod())
              .build();
        }
      }
    }
    return result;
  }
}
