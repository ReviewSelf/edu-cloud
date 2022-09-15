package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@TableName("lesson_select")
public class EnrollJoinLessonEntity {

    private Integer id;
    private Integer tryLesson;

}
