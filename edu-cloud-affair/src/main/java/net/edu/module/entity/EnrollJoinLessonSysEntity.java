package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@TableName("lesson_select")
public class EnrollJoinLessonSysEntity {

    private String username;
    private String realName;
    private Integer moblie;
    private String unionId;
    private String openId;
    private String area;
    private Integer age;
    private Integer grade;
    private Integer version;
    private Integer integral;
    private Integer balance;

}
