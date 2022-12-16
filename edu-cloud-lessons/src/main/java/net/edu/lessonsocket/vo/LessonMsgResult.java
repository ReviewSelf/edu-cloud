package net.edu.lessonsocket.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 马佳浩
 * @date 2022/12/1 13:00:23
 * @description
 */
@Data
@AllArgsConstructor
public class LessonMsgResult<T> {

    private T data;
    private int type;

    /**
     *
     *
     *
     */


}
