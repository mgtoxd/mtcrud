package pers.mtx;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.util.ResourceLeakDetector;
import org.apache.rocketmq.client.apis.ClientException;
import pers.mtx.Handler.filterHandler;
import pers.mtx.init.DataStructure;
import pers.mtx.service.MtCrudGrpcServiceImpl;
import pers.mtx.util.LogUtil;
import pers.mtx.util.YmlUtil;

import java.io.IOException;
import java.util.Objects;

import static pers.mtx.service.RocketMqServ.initRocketMqServ;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, ClientException {

        DataStructure structure = new DataStructure();
        try {
            structure.getDataStructure();
        } catch (Exception e) {
            LogUtil.getExceptionInfo(e);
            System.out.println("数据库初始化失败");
        }
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup worker = new NioEventLoopGroup(16);
        ServerBootstrap bootstrap = new ServerBootstrap();
        ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.ADVANCED);
        bootstrap.channel(NioServerSocketChannel.class)
                .group(boss, worker)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ch.pipeline()
                                .addLast(new HttpServerCodec())
                                .addLast(new HttpObjectAggregator(65536))
                                .addLast(new filterHandler())
                        ;
                    }
                }).bind(YmlUtil.getSetting().getServer().getPort());
        System.out.println("Gprc正在启动");
        Server server = ServerBuilder.
                forPort(YmlUtil.getSetting().getServer().getGrpc_port())
                .addService(new MtCrudGrpcServiceImpl())
                .build().start();
        System.out.printf("GRpc服务端启动成功, 端口号: %d.%n", YmlUtil.getSetting().getServer().getGrpc_port());

        if (Objects.nonNull(YmlUtil.getSetting().getRocketmq())){
            initRocketMqServ();
        }
        server.awaitTermination();
    }



}
