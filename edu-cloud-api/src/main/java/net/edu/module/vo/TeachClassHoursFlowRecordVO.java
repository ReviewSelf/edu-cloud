package net.edu.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author weng
 * @date 2023/3/6 - 16:36
 **/
@Data
@Schema(description = "课时流水管理")
public class TeachClassHoursFlowRecordVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "自增id")
    private Long id;

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "用户名称")
    private String userName;

    @Schema(description = "课堂id")
    private Long lessonId;

    @Schema(description = "课堂名称")
    private String lessonName;

    @Schema(description = "说明")
    private String direction;

    @Schema(description = "常规课时")
    private BigDecimal normal;

    @Schema(description = "常规赠送课时")
    private BigDecimal normalPresent;

    @Schema(description = "集训课时")
    private BigDecimal training;

    @Schema(description = "集训赠送课时")
    private BigDecimal trainingPresent;

    @Schema(description = "类型：0是退课时、1是加课时、2是扣课时")
    private Integer type;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date createTime;

    @Schema(description = "版本")
    private Integer version;

    @Schema(description = "修改时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date updateTime;

    @Schema(description = "创建人")
    private Long creator;

    @Schema(description = "更新人")
    private Long updater;

    @Schema(description = "删除")
    private Integer deleted;
}