package net.edu.lessonsocket.vo;

import lombok.Data;

/**
 * @author 马佳浩
 * @date 2022/12/9 12:16:42
 * @description
 */
@Data
public class LessonUserVO {
    //把签到表 + 评价表集成
    Long userId;
    Long lessonId;
    Long username;
    String ability;
    Integer submitTimes;
    Integer correctTimes;
}
