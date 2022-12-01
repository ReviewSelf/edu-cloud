package net.edu.module.vo;

import lombok.Data;
import org.apache.poi.hpsf.Decimal;

import java.math.BigDecimal;

@Data
public class ArchiveAssessByCourseIdVo {

    private Integer assessId;

    private String name;

    private BigDecimal weight;

    private Integer courseId;

    private Integer targetId;

    private Integer mannerId;

    private String mannerName;

    private BigDecimal mannerWeight;

    private Integer mannerKind;
}
