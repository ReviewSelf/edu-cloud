package net.edu.module.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/7 16:25
 * @Version: 1.0
 * @Description:
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JudgeResultVO {
    private Long recordId;
    private Long sampleId;
    private Integer resultCode;
    private BigDecimal runtime;
    private Integer memory;
    private BigDecimal passRate;
}
