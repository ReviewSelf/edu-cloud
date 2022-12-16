package net.edu.lessonsocket.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 马佳浩
 * @date 2022/11/30 14:17:09
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonMsg<T> {
    /**
     * 发送者
     */
    private Long userId;

    /**
     * 场景
     */

    /**
     * 课堂场景
     */
    private Long lessonId;


    /**
     * 消息值
     */
    private T data;


    /**
     * 类型 见枚举类
     */
    private int type;


}
