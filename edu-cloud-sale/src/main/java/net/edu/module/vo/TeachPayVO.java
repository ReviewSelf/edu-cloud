package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

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
	private Integer balance;

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
	private Date time;

	@Schema(description = "试听情况")
	private Integer type;

	@Schema(description = "备注")
	private String bz;

	@Schema(description = "经手人")
	private String handler;

	@Schema(description = "状态")
	private Integer status;
}