package net.edu.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author weng
 * @date 2022/11/3 - 11:35
 **/
@Data
@Schema(description = "评测点权重")
public class ArchiveWeightAssessTestVO {
    @Schema(description = "编号")
    private Long id;

    @Schema(description = "来源")
    private String source;

    @Schema(description = "名字")
    private String name;

    private String assessName;

    @Schema(description = "评测点id")
    private Long testId;

    private Long courseId;

    @Schema(description = "考核点id")
    private Long assessId;

    @Schema(description = "权重")
    private BigDecimal weight;

    @Schema(description = "删除")
    private Integer deleted;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date createTime;

    @Schema(description = "修改时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date updateTime;

    @Schema(description = "创建人")
    private String creator;

    @Schema(description = "版本号")
    private Integer version;

    @Schema(description = "修改人")
    private String updater;
}
