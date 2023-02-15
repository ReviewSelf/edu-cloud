package net.edu.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
* 课时流水表
*
* @author sqw 
* @since 1.0.0 2023-02-15
*/
@Data
@Schema(description = "课时流水表")
public class ClassHoursFlowRecordVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "自增id")
	private Long id;

	@Schema(description = "班级id")
	private Long classId;

	@Schema(description = "用户id")
	private Long userId;

	@Schema(description = "课时数")
	private Float classHours;

	@Schema(description = "备注")
	private String remarks;

	@Schema(description = "场景：0是退班、1是插班、3是教务续费、4是销售充值")
	private Integer scene;

	@Schema(description = "状态：0是退费、1是充值、2是扣费")
	private Integer status;

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