package net.edu.lessonsocket.handler;

import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.util.URLUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 马佳浩
 * @date 2022/12/16 11:17:17
 * @description
 */
@Component
@ChannelHandler.Sharable
@Slf4j
public class NettyParamHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        String uri = request.uri();
        log.info("NettyWebSocketParamHandler.channelRead0 --> : 格式化URL... {}", uri);
        String token=null;
        try {
            token=uri.split("token=")[1];
        }catch (Exception e){
            token=null;
        }

        //将参数放入通道中传递下去
        AttributeKey<String> attributeKey = AttributeKey.valueOf("token");
        ctx.channel().attr(attributeKey).setIfAbsent(token);
        request.setUri(URLUtil.getPath(uri));
        ctx.fireChannelRead(request.retain());
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        log.error("NettyWebSocketParamHandler.exceptionCaught --> cause: ", cause);
        ctx.close();
    }
}
