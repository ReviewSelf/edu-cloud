package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;

import net.edu.framework.common.utils.DateUtils;
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
	private String payment;

	@Schema(description = "实际金额")
	private String paymentTrue;

	@Schema(description = "用户id")
	private Integer userId;

	@Schema(description = "缴费时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date time;

	@Schema(description = "操作类型")
	private Integer type;

	@Schema(description = "备注")
	private String bz;


}