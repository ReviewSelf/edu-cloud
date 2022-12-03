package net.edu.module.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ArchiveAssessTestScoreVo {

    private String assessName;

    private BigDecimal assessScore;

    private BigDecimal weight;
}
