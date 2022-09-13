package net.edu.module.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/12 13:29
 * @Version: 1.0
 * @Description:
 */
@Data
public class CodeProblemSubmitVO {
    private Long recordId;
    private Long problemId;
    private String submitCode;
    private Integer sampleNum;
    private Integer languageType;
    private Integer memoryLimit;
    private BigDecimal timeLimit;
}
