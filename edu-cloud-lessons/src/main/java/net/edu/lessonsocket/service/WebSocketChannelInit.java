package net.edu.lessonsocket.service;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import net.edu.lessonsocket.config.LessonNettyConfig;
import net.edu.lessonsocket.handler.LessonNettyHandler;
import net.edu.lessonsocket.handler.NettyParamHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 马佳浩
 * @date 2022/11/30 14:18:40
 * @description
 */
@Component
public class WebSocketChannelInit extends ChannelInitializer {

    @Autowired
    LessonNettyHandler lessonNettyHandler;

    @Autowired
    NettyParamHandler nettyParamHandler;

    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        //对http协议的支持.
        pipeline.addLast(new HttpServerCodec());
        // http 消息聚合器  512*1024为接收的最大content-length
        pipeline.addLast(new HttpObjectAggregator(8192));
        // 对大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());
        //设置一个空闲状态处理程序（心跳机制），读空闲，写空闲，读写空闲
        pipeline.addLast(new IdleStateHandler(20, 0, 0));
        //post请求分三部分. request line / request header / message body
        // HttpObjectAggregator将多个信息转化成单一的request或者response对象
        //pipeline.addLast(new HttpObjectAggregator(8000));
        //token截取
        pipeline.addLast(nettyParamHandler);
        // 将http协议升级为ws协议. websocket的支持
        pipeline.addLast(new WebSocketServerProtocolHandler(LessonNettyConfig.PATH));
        // 自定义处理handler
        pipeline.addLast(lessonNettyHandler);
    }
}
