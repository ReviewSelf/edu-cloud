package net.edu.lessonsocket.service.observer;

import cn.hutool.json.JSONUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import net.edu.lessonsocket.config.LessonProperties;
import net.edu.lessonsocket.enums.LessonMsgResultTypeEnum;
import net.edu.lessonsocket.enums.LessonMsgTypeEnum;
import net.edu.lessonsocket.vo.LessonMsg;
import net.edu.lessonsocket.vo.LessonMsgResult;
import net.edu.module.query.LessonAttendLogQuery;
import net.edu.module.service.LessonAttendLogService;
import net.edu.module.vo.LessonAttendLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserObserver implements ApplicationListener<LessonMsgEvent> {

    @Autowired
    private LessonAttendLogService lessonAttendLogService;

    @Override
    public void onApplicationEvent(LessonMsgEvent event) {

        if(event.getType()!= LessonMsgTypeEnum.GET_USER.getType()){
            return;
        }
        LessonMsg message = JSONUtil.toBean(event.getTextWebSocketFrame().text(), LessonMsg.class);
        ChannelHandlerContext handlerContext = LessonProperties.USER_CHANNEL.get(message.getUserId());
        LessonMsgResult result = new LessonMsgResult<>(LessonProperties.LESSON_USERS.get(message.getLessonId()), LessonMsgResultTypeEnum.USER_DATA.getType());
       // System.out.println(LessonProperties.LESSON_USERS.get(message.getLessonId()));
        handlerContext.writeAndFlush(new TextWebSocketFrame(JSONUtil.toJsonStr(result)));
    }
}
