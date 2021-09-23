package pers.mtx;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.ResourceLeakDetector;
import pers.mtx.Handler.filterHandler;
import pers.mtx.init.DataStructure;
import pers.mtx.util.YmlUtil;

public class main {
    public static void main(String[] args) {

        DataStructure structure = new DataStructure();
        try {
            structure.getDataStructure();
        } catch (Exception e) {
            e.printStackTrace();
        }
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup worker = new NioEventLoopGroup(16);
        ServerBootstrap bootstrap = new ServerBootstrap();
        ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.ADVANCED);
        bootstrap.channel(NioServerSocketChannel.class)
                .group(boss, worker)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new HttpServerCodec())
                                .addLast(new HttpObjectAggregator(65536))
                                .addLast(new filterHandler())
                        ;
                    }
                }).bind(YmlUtil.getSetting().getServer().getPort());

    }

}
