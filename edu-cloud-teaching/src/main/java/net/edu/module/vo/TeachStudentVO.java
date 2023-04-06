package net.edu.module.vo;

import lombok.Data;

import java.math.BigDecimal;

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
    /**
     *  剩余集训课时
     */
    private BigDecimal training;
    /**
     *  剩余集训赠送课时
     */
    private BigDecimal trainingPresent;
    /**
     *  剩余普通赠送课时
     */
    private BigDecimal normalPresent;
    /**
     *  剩余普通课时
     */
    private BigDecimal normal;
}
