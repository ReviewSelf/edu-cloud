package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.mybatis.entity.BaseEntity;

@Data
@EqualsAndHashCode(callSuper=false)
@TableName("enroll_evaluate")
public class EnrollOpinionEntity extends BaseEntity {

    /**
     * 学生ID
     */
    private Integer studentId;

    /**
     * 任课老师
     */
    private String teacherOpinion;

}
