package net.edu.lessonsocket.service.observer;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import net.edu.framework.common.utils.DateUtils;
import net.edu.lessonsocket.config.LessonProperties;
import net.edu.lessonsocket.enums.LessonMsgResultTypeEnum;
import net.edu.lessonsocket.enums.LessonMsgTypeEnum;
import net.edu.lessonsocket.vo.LessonMsgResult;
import net.edu.module.service.LessonAttendLogService;
import net.edu.module.service.LessonChatLogService;
import net.edu.module.vo.LessonChatLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 马佳浩
 * @date 2022/12/16 09:53:07
 * @description
 */

@Component
public class SendMsgObserver{

    @Autowired
    private LessonChatLogService lessonChatLogService;


    //两种方法实现监听  重构 ApplicationListener<LessonMsgEvent> or @EventListener 注解
    @EventListener
    public void sendMsg(LessonMsgEvent event) {
        if(event.getType()!= LessonMsgTypeEnum.SEND_MSG.getType()){
            return;
        }

        LessonChatLogVO vo = JSONUtil.toBean(event.getTextWebSocketFrame().text(), LessonChatLogVO.class);
        vo.setCreateTime(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
        //发送消息
        LessonMsgResult result = new LessonMsgResult(vo, LessonMsgResultTypeEnum.CHAT_DATA.getType());

        //保存记录
        lessonChatLogService.save(vo);

        // 给课堂中其他用户 发送消息
        if (CollUtil.isNotEmpty(LessonProperties.LESSON_USERS.get(vo.getLessonId()))) {
            for (Long userId : LessonProperties.LESSON_USERS.get(vo.getLessonId())) {
                ChannelHandlerContext handlerContext = LessonProperties.USER_CHANNEL.get(userId);
                if (handlerContext == event.getChannelHandlerContext()) {
                    //排除自身,自身不需要通知
                    continue;
                }
                handlerContext.writeAndFlush(new TextWebSocketFrame(JSONUtil.toJsonStr(result)));
            }
        }
    }
}
