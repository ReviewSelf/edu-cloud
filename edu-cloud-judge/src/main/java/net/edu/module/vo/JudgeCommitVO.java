package net.edu.module.vo;


import lombok.Data;


/**
 * @Author: 马佳浩
 * @Date: 2022/9/7 14:55
 * @Version: 1.0
 * @Description:
 */
@Data
public class JudgeCommitVO {
    private Integer problemId;
    private String code;
    private Double timeLimit;
    private Integer languageId;
    private Integer memoryLimit;


}
