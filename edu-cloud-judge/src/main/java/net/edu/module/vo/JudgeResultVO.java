package net.edu.module.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/7 16:25
 * @Version: 1.0
 * @Description:
 */
//这里为了适配juage0服务返回值采用下划线命名
@Data
public class JudgeResultVO {
    private Integer status_id;
    private BigDecimal time;
    private Integer memory;
}
