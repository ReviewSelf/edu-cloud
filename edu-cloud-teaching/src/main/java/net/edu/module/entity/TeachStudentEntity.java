package net.edu.module.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.mybatis.entity.BaseEntity;

@Data
@EqualsAndHashCode(callSuper=false)
@TableName("teach_class_user")
public class TeachStudentEntity extends BaseEntity {

    /**
     * 学生用户名
     */
    private String username;

    /**
     * 学生姓名
     */
    private String realName;

    /**
     * 学生电话号码
     */
    private String mobile;

    /**
     *  学生id
     */
    private Long userId;

    /**
     *  班级id
     */
    private Long classId;
}
