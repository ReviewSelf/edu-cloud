package net.edu.module.vo;

import lombok.Data;

/**
 * @Author: 马佳浩
 * @Date: 2022/11/10 18:16
 * @Version: 1.0
 * @Description:
 */
@Data
public class ProblemFinishVo {
    private Long problemId;
    private Integer problemType;
    // 0=未判题，3=正确，4=错误
    private Integer submitStatus;
}
