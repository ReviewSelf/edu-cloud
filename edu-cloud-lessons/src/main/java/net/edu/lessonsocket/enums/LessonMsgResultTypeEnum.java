package net.edu.lessonsocket.enums;

import lombok.Getter;

/**
 * @author 马佳浩
 * @date 2022/12/7 20:39:34
 * @description 服务端发送消息
 */
@Getter
public enum LessonMsgResultTypeEnum {

    /**
     * 发送xx用户下线通知
     */
    LOGOFF_DATA(0),

    /**
     * 发送xx用户上线通知
     */
    LOGIN_DATA(1),
    /**
     * 传达用户消息
     */
    CHAT_DATA(2),
    /**
     *获取课堂在线用户列表
     */
    USER_DATA(3);


    private final int type;

    LessonMsgResultTypeEnum(int type) {
        this.type = type;
    }
}
