package net.edu.module.vo;

import lombok.Data;
import org.apache.poi.hpsf.Decimal;

import java.math.BigDecimal;

@Data
public class ArchiveAssessTableVo {

    //最终结果
    private String rouCome[][];

    //教学目标个数
    private Integer targetNum;

    //考核点个数
    private Integer assessNum;

    //考核点的占比数组
    private BigDecimal assessWeightArr[][];

    //教学目标的占比数组
    private BigDecimal targetWeightArr[];

}
