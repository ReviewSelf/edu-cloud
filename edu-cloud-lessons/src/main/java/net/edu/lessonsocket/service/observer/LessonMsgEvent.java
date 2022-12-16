package net.edu.lessonsocket.service.observer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 马佳浩
 * @date 2022/12/16 09:42:58
 * @description
 */


@Getter
@Setter
public class LessonMsgEvent extends ApplicationEvent {


    private int type;

    private ChannelHandlerContext channelHandlerContext;

    private TextWebSocketFrame textWebSocketFrame;

    public LessonMsgEvent(Object source) {
        super(source);
    }
    public LessonMsgEvent(Object source,ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame, int type) {
        super(source);
        this.channelHandlerContext=channelHandlerContext;
        this.type=type;
        this.textWebSocketFrame=textWebSocketFrame;
    }




}
