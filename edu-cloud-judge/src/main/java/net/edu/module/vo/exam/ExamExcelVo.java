package net.edu.module.vo.exam;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 导出考试成绩
 *
 * @author 小樊 babamu@126.com
 * @since 1.0.0 2022-11-01
 */
@Data
@Schema(description = "导出考试成绩")
public class ExamExcelVo {

    @Schema(description = "考试题目")
    private String problemName;
    @Schema(description = "答题内容")
    private String submitContent;
    @Schema(description = "得分")
    private BigDecimal fraction;

}
