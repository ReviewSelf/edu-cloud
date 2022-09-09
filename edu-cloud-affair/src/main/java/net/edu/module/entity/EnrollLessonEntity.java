package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.mybatis.entity.BaseEntity;

@Data
@EqualsAndHashCode(callSuper=false)
@TableName("teach_lesson")
public class EnrollLessonEntity extends BaseEntity {

    /**
     * 任课老师
     */
    private String username;

    /**
     * 任课老师ID
     */
    private Integer teacherId;

    /**
     * 上课地点
     */
    private String place;

    /**
     * 开课时间
     */
    private String beginTime;

    /**
     * 结课时间
     */
    private String endTime;

}
