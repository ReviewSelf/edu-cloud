package net.edu.lessonsocket.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 马佳浩
 * @date 2022/11/30 15:05:59
 * @description 客户端发送消息
 */
@Getter
public enum LessonMsgTypeEnum {
    /**
     * 用户进入课堂
     */
    LOGIN_LESSON(1),
    /**
     * 发送消息
     */
    SEND_MSG(2),

    /**
     *获取课堂在线用户列表
     */
    GET_USER(3);

    private final int type;

    LessonMsgTypeEnum(int type) {
        this.type = type;
    }
}
