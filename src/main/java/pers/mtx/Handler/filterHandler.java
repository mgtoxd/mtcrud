package pers.mtx.Handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import pers.mtx.mt.Crud;
import pers.mtx.mt.CrudFactory;
import pers.mtx.service.CustomServ;
import pers.mtx.util.YmlUtil;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;


/**
 * （HTTP）业务分类，复杂、简单查询和删除，增加，更新
 */
public class filterHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest fullRequest = (FullHttpRequest) msg;
        String uri = fullRequest.uri();
        byte[] bytes1 = new byte[fullRequest.content().readableBytes()];
        fullRequest.content().readBytes(bytes1);
        fullRequest.content().release();
        if (uri.indexOf("/mget") == 0) {
            // 复杂查询
            Crud crud = CrudFactory.getCrudByName("mget");
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(fullRequest.protocolVersion(), HttpResponseStatus.OK);
            byte[] bytes = crud.make(uri, bytes1).getBytes(StandardCharsets.UTF_8);
            response.headers().set(CONTENT_LENGTH, bytes.length);
            response.content().writeBytes(bytes);
            ctx.writeAndFlush(response);
            ctx.close();
        } else if (uri.indexOf("/mt") == 0) {
            //进入默认crud
            //System.out.println("mt");
            Crud crud = CrudFactory.getCrud(fullRequest.method());
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(fullRequest.protocolVersion(), HttpResponseStatus.OK);
            byte[] bytes = crud.make(uri, bytes1).getBytes(StandardCharsets.UTF_8);
            response.headers().set(CONTENT_LENGTH, bytes.length);
            response.content().writeBytes(bytes);
            ctx.writeAndFlush(response);
            ctx.close();
        } else {
            //自定义接口
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(fullRequest.protocolVersion(), HttpResponseStatus.OK);
            String sql = Objects.requireNonNull(YmlUtil.getSql()).get(uri.replaceFirst("/", ""));
            byte[] rs = CustomServ.getInstance().executeCustomSql(sql, bytes1).getBytes(StandardCharsets.UTF_8);
            response.headers().set(CONTENT_LENGTH, rs.length);
            response.content().writeBytes(rs);
            ctx.writeAndFlush(response);
            ctx.close();
        }
    }
}
