package net.edu.lessonsocket.handler;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import net.edu.framework.common.utils.UserTokenContext;
import net.edu.framework.security.cache.TokenStoreCache;
import net.edu.framework.security.user.UserDetail;
import net.edu.lessonsocket.config.LessonProperties;
import net.edu.lessonsocket.enums.LessonMsgResultTypeEnum;
import net.edu.lessonsocket.service.observer.LessonMsgEvent;
import net.edu.lessonsocket.vo.LessonMsgResult;
import net.edu.module.query.LessonAttendLogQuery;
import net.edu.module.service.LessonAttendLogService;
import net.edu.module.vo.LessonAttendLogVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;


import java.util.*;

/**
 * @author 马佳浩
 * @date 2022/11/24 18:11:49
 * @description
 */
@ChannelHandler.Sharable
@Component
@Slf4j
public class LessonNettyHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> implements ApplicationEventPublisherAware {


    @Autowired
    private LessonAttendLogService lessonAttendLogService;

    @Autowired
    private  TokenStoreCache tokenStoreCache;

    private ApplicationEventPublisher applicationEventPublisher;
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }


    /**
     * 通道连接事件
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LessonProperties.LIST_LOBBY.add(ctx);
        log.info("有新的连接.>>当前连接数量:{}" , LessonProperties.LIST_LOBBY.size());
        super.channelActive(ctx);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object evt) throws Exception {
        //检查用户token
        AttributeKey<String> attributeKey = AttributeKey.valueOf("token");
        //从通道中获取用户token
        String token = ctx.channel().attr(attributeKey).get();
        //校验token逻辑
        boolean flag = false;
        System.out.println(token);
        if (StringUtils.isNotBlank(token)) {
            // 获取登录用户信息
            UserDetail user = tokenStoreCache.getUser(token);
            System.out.println(user);
            if (user != null) {
                flag = true;
                //将Token放入当前线程上下文中
                UserTokenContext.setToken(token);
            }
        }
        if (!flag) {
            LessonProperties.LIST_LOBBY.remove(ctx);
            ctx.channel().close();
        }
        else {
            super.channelRead(ctx, evt);
        }
    }

    /**
     * 通道消息事件
     *
     * @param channelHandlerContext
     * @param textWebSocketFrame
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        log.info("前端发来的消息:{}" , textWebSocketFrame.text());
        JSONObject object = new JSONObject(textWebSocketFrame.text());
        Integer type = object.getInt("type");
        applicationEventPublisher.publishEvent(new LessonMsgEvent(this,channelHandlerContext,textWebSocketFrame,type));
    }


    /**
     * 通达关闭事件
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("用户离线");
        Long mapKey = null;
        ChannelHandlerContext mapValue = null;
        for (Map.Entry<Long, ChannelHandlerContext> entry : LessonProperties.USER_CHANNEL.entrySet()) {
            mapKey = entry.getKey();
            mapValue = entry.getValue();
            if (mapValue.equals(ctx)) {
                LessonProperties.USER_CHANNEL.remove(mapKey);
                break;
            }
        }
        if (mapKey != null) {
            Long lessonId = LessonProperties.USER_LESSON.get(mapKey);
            LessonProperties.LESSON_USERS.get(lessonId).remove(mapKey);
            LessonProperties.USER_LESSON.remove(mapKey);
            List<LessonAttendLogVO> users = lessonAttendLogService.list(new LessonAttendLogQuery(lessonId));
            for (int i = 0; i < users.size(); i++) {
                boolean flag = LessonProperties.LESSON_USERS.get(lessonId).contains(users.get(i).getStuId());
                users.get(i).setOnlineFlag(flag);
            }
            HashMap<String, Object> data = new HashMap();
            data.put("users", users);
            LessonMsgResult result = new LessonMsgResult(data, LessonMsgResultTypeEnum.LOGOFF_DATA.getType());
            for (Long userId : LessonProperties.LESSON_USERS.get(lessonId)) {
                ChannelHandlerContext handlerContext = LessonProperties.USER_CHANNEL.get(userId);
                handlerContext.writeAndFlush(new TextWebSocketFrame(JSONUtil.toJsonStr(result)));
            }

        }
        LessonProperties.LIST_LOBBY.remove(ctx);
    }

    /**
     * 异常机制
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("webSocket错误:{}",cause);
        Long mapKey = null;
        ChannelHandlerContext mapValue = null;
        for (Map.Entry<Long, ChannelHandlerContext> entry : LessonProperties.USER_CHANNEL.entrySet()) {
            mapKey = entry.getKey();
            mapValue = entry.getValue();
            if (mapValue.equals(ctx)) {
                LessonProperties.USER_CHANNEL.remove(mapKey);
                break;
            }
        }
        LessonProperties.LESSON_USERS.get(LessonProperties.USER_LESSON.get(mapKey)).remove(mapKey);
        LessonProperties.USER_LESSON.remove(mapKey);
        LessonProperties.LIST_LOBBY.remove(ctx);
    }



}
