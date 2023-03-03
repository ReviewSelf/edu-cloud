package net.edu.module.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
* 流水账管理
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-09-16
*/
@Data
@Schema(description = "流水账管理")
public class TeachPayVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "缴费记录id")
	private Integer id;

	@Schema(description = "缴费金额")
	private BigDecimal payment;

	@Schema(description = "购买课次")
	private BigDecimal classHours;

	@Schema(description = "充值课程类型 0为常规，1为集训")
	private Integer classType;

	@Schema(description = "赠送课程类型 0为常规，1为集训")
	private Integer presentType;

	@Schema(description = "赠送课次")
	private BigDecimal presentHours;

	@Schema(description = "用户id")
	private Long userId;

	@Schema(description = "学生姓名")
	private String realName;

	@Schema(description = "学生学号")
	private String stuNumber;

	@Schema(description = "手机号")
	private String mobile;

	@Schema(description = "绑定人身份")
	private String urgentIdentity;

	@Schema(description = "销售姓名")
	private String saleName;

	@Schema(description = "缴费时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date time;

	@Schema(description = "缴费 或 退费标志")
	private Integer isPay;

	@Schema(description = "备注")
	private String bz;

	@Schema(description = "经手人")
	private String handler;

	@Schema(description = "状态")
	private Integer status;
}