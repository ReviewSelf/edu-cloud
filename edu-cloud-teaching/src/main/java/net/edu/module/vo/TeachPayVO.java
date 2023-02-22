package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;

import net.edu.framework.common.utils.DateUtils;

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

	@Schema(description = "缴费时间")
	private Date time;

	@Schema(description = "操作类型")
	private Integer type;

	@Schema(description = "备注")
	private String bz;

	@Schema(description = "经手人")
	private String handler;

	@Schema(description = "状态")
	private Integer status;
}