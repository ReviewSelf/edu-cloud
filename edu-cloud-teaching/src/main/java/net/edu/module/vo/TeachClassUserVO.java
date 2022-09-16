package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
* 班级用户表
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-09-16
*/
@Data
@Schema(description = "班级用户表")
public class TeachClassUserVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "自增主键")
	private Long id;

	@Schema(description = "班级id")
	private Long classId;

	@Schema(description = "用户id")
	private Long userId;

	@Schema(description = "加入课次")
	private Integer joinLessonTimes;

	@Schema(description = "退出课次")
	private Integer quitLessonTimes;

	@Schema(description = "加入时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date joinTime;

	@Schema(description = "退出时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date quitTime;

	@Schema(description = "1=正常，2=退班")
	private Integer status;

	@Schema(description = "迟到次数")
	private Integer lateTimes;

	@Schema(description = "作业按时提交次数(每次作业截止时统计。)")
	private Integer homeworkTimes;

	@Schema(description = "作业未完成题目总数量(每次作业截止时统计。)")
	private Integer unfinishedProblemNum;

	@Schema(description = "缴费金额")
	private String paymentAmount;

	@Schema(description = "缴费时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date paymentTimes;

	@Schema(description = "实际金额")
	private String paymentTrue;

	@Schema(description = "创建时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;

	@Schema(description = "更新时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date updateTime;

	@Schema(description = "逻辑删除")
	private Integer deleted;

	@Schema(description = "更新人")
	private Long updater;

	@Schema(description = "创建人")
	private Long creator;

	@Schema(description = "版本")
	private Integer version;


}