package net.edu.module.vo;

import lombok.Data;

@Data
public class TeachStudentVO {
    /**
     * 学生账号
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
    private Integer userId;

}
