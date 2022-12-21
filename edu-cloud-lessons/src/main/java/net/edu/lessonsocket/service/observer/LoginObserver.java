package net.edu.lessonsocket.service.observer;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import net.edu.lessonsocket.config.LessonProperties;
import net.edu.lessonsocket.enums.LessonMsgResultTypeEnum;
import net.edu.lessonsocket.enums.LessonMsgTypeEnum;
import net.edu.lessonsocket.vo.LessonMsg;
import net.edu.lessonsocket.vo.LessonMsgResult;
import net.edu.module.query.LessonAttendLogQuery;
import net.edu.module.service.LessonAttendLogService;
import net.edu.module.service.impl.LessonAttendLogServiceImpl;
import net.edu.module.vo.LessonAttendLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @author 马佳浩
 * @date 2022/12/16 09:47:59
 * @description
 */
@Slf4j
@Component
public class LoginObserver implements ApplicationListener<LessonMsgEvent> {

    @Autowired
    private LessonAttendLogService lessonAttendLogService;


    @Override
    public void onApplicationEvent(LessonMsgEvent event) {
        if(event.getType()!= LessonMsgTypeEnum.LOGIN_LESSON.getType()){
            return;
        }
        //用户上线
        LessonMsg message = JSONUtil.toBean(event.getTextWebSocketFrame().text(), LessonMsg.class);
        JSONObject object = new JSONObject(event.getTextWebSocketFrame().text());
        setMap(event.getChannelHandlerContext(), message);

        List<LessonAttendLogVO> users = lessonAttendLogService.list(new LessonAttendLogQuery(message.getLessonId()));
        for (int i = 0; i < users.size(); i++) {
            boolean flag = LessonProperties.LESSON_USERS.get(message.getLessonId()).contains(users.get(i).getStuId());
            users.get(i).setOnlineFlag(flag);
        }
        HashMap<String, Object> data = new HashMap(8);
        data.put("userIdList", LessonProperties.LESSON_USERS.get(message.getLessonId()));
        data.put("msg",   object.getStr("username") + "进来了");

        LessonMsgResult result = new LessonMsgResult(data, LessonMsgResultTypeEnum.LOGIN_DATA.getType());
        // 给课堂中其他用户 发送上线消息
        for (Long userId : LessonProperties.LESSON_USERS.get(message.getLessonId())) {
            ChannelHandlerContext handlerContext = LessonProperties.USER_CHANNEL.get(userId);
//                if (handlerContext==channelHandlerContext) {
//                    //排除自身,自身不需要通知
//                    continue;
//                }
            handlerContext.writeAndFlush(new TextWebSocketFrame(JSONUtil.toJsonStr(result)));
        }
    }



    /**
     * 设置连接映射
     *
     * @param channelHandlerContext
     * @param message
     */
    private void setMap(ChannelHandlerContext channelHandlerContext, LessonMsg message) {
        //保存用户关联通道
        LessonProperties.USER_CHANNEL.put(message.getUserId(), channelHandlerContext);
        if (LessonProperties.USER_LESSON.get(message.getUserId()) != null) {
            //删除原有房间user
            LessonProperties.LESSON_USERS.get(LessonProperties.USER_LESSON.get(message.getUserId())).remove(message.getUserId());
        }
        LessonProperties.USER_LESSON.put(message.getUserId(), message.getLessonId());

        //存放课堂用户
        if (LessonProperties.LESSON_USERS.get(message.getLessonId()) == null) {
            HashSet<Long> users = new HashSet<>();
            users.add(message.getUserId());
            LessonProperties.LESSON_USERS.put(message.getLessonId(), users);

        } else {
            LessonProperties.LESSON_USERS.get(message.getLessonId()).add(message.getUserId());
        }
        log.info("课堂" + message.getLessonId() + "进入一位学生" + message.getUserId() + "目前共" + LessonProperties.LESSON_USERS.get(message.getLessonId()).size() + "位学生");
    }


}
