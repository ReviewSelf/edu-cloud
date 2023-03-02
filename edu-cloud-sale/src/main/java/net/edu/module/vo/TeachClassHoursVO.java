package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;

import net.edu.framework.common.utils.DateUtils;
import java.math.BigDecimal;
import java.util.Date;

/**
* 课时
*
* @author weng babamu@126.com
* @since 1.0.0 2023-03-02
*/
@Data
@Schema(description = "课时")
public class TeachClassHoursVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@Schema(description = "用户id")
	private Long userId;

	@Schema(description = "学生姓名")
	private String realName;

	@Schema(description = "学号")
	private String stuNumber;

	@Schema(description = "常规课时")
	private BigDecimal normal;

	@Schema(description = "集训课时")
	private BigDecimal training;

	@Schema(description = "常规赠送课时")
	private BigDecimal normalPresent;

	@Schema(description = "集训赠送课时")
	private BigDecimal trainingPresent;

	@Schema(description = "充值总额")
	private BigDecimal totalMoney;

	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;

	private Long creator;

	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date updateTime;

	@Schema(description = "更新者")
	private Long updater;

	@Schema(description = "删除标识  0：正常   1：已删除")
	private Integer deleted;

	@Schema(description = "版本号")
	private Integer version;


}