package pers.mtx.Handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import pers.mtx.mt.Crud;
import pers.mtx.mt.CrudFactory;

import java.nio.charset.StandardCharsets;
import java.util.logging.SocketHandler;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;

public class filterHandler extends ChannelInboundHandlerAdapter{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest fullRequest = (FullHttpRequest) msg;
        String uri = fullRequest.uri();
        byte[] bytes1 = new byte[fullRequest.content().readableBytes()];
        fullRequest.content().readBytes(bytes1);
        if (uri.indexOf("/mt")==0){
            //进入默认crud
            System.out.println("mt");
            Crud crud = CrudFactory.getCrud(fullRequest.method());
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(fullRequest.protocolVersion(), HttpResponseStatus.OK);
            byte[] bytes = crud.make(uri, bytes1).getBytes(StandardCharsets.UTF_8);
            response.headers().set(CONTENT_LENGTH,bytes.length);
            response.content().writeBytes(bytes);
            ctx.writeAndFlush(response);
        }else {
            //转发到相应接口
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(fullRequest.protocolVersion(), HttpResponseStatus.OK);
            byte[] bytes = "<h1>hellod</h1>".getBytes(StandardCharsets.UTF_8);
            response.headers().set(CONTENT_LENGTH,bytes.length);
            response.content().writeBytes(bytes);
            ctx.writeAndFlush(response);
        }
    }
}
