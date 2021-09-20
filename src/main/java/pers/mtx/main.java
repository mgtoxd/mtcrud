package pers.mtx;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import pers.mtx.Handler.filterHandler;
import pers.mtx.init.DataStructure;

import java.nio.charset.StandardCharsets;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;

public class main {
    public static void main(String[] args) {
        DataStructure structure = new DataStructure();
        try {
            structure.getDataStructure();
        } catch (Exception e) {
            e.printStackTrace();
        }
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.channel(NioServerSocketChannel.class)
                .group(boss,worker)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new HttpServerCodec())
                                .addLast(new HttpObjectAggregator(65536))
                                .addLast(new filterHandler())
//                        new ChannelInboundHandlerAdapter() {
//                            @Override
//                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//                                System.out.println(msg.getClass());
//                                if (msg instanceof HttpRequest){
//
//                                }else if (msg instanceof HttpContent){
//
//                                }
//                                super.channelRead(ctx,msg);
//                            }
//
//                        }
//                                .addLast(new SimpleChannelInboundHandler<HttpContent>() {
//                                    @Override
//                                    protected void channelRead0(ChannelHandlerContext ctx, HttpContent msg) throws Exception {
//
//                                        ByteBuf buf = msg.content();
//                                        byte[] bytes = new byte[buf.readableBytes()];
//                                        buf.readBytes(bytes);
//                                        String s = new String(bytes, StandardCharsets.UTF_8);
//                                        System.out.println(s);
//
//                                    }
//                                })
//                                .addLast(new SimpleChannelInboundHandler<HttpRequest>() {
//                            @Override
//                            protected void channelRead0(ChannelHandlerContext ctx, HttpRequest msg) throws Exception {
//                                //获取请求
//                                System.out.println(msg.uri());
//                                HttpHeaders headers = msg.headers();
//                                String s = headers.get("content-type");
//                                System.out.println("type="+s);
//
//                                //返回响应
//                                DefaultFullHttpResponse response = new DefaultFullHttpResponse(msg.protocolVersion(), HttpResponseStatus.OK);
//                                byte[] bytes = "<h1>hellod</h1>".getBytes(StandardCharsets.UTF_8);
//                                //设置返回体长度,否则浏览器会一直等待
//                                response.headers().setInt(CONTENT_LENGTH,bytes.length);
//                                response.content().writeBytes(bytes);
//
//                                ctx.writeAndFlush(response);
//                            }
//                        });
;
                    }
                }).bind(8080);

    }

}
