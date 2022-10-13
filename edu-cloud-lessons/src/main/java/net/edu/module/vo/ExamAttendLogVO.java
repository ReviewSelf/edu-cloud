package net.edu.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
* 课堂签到表
*
* @author 马佳浩 
* @since 1.0.0 2022-09-15
*/
@Data
@Schema(description = "课堂签到表")
public class ExamAttendLogVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@Schema(description = "学生id")
	private Long userId;

	@Schema(description = "学生姓名")
	private String name;

	@Schema(description = "考试id")
	private Long examId;

	@Schema(description = "参加时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date joinTime;

	@Schema(description = "结束时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date quitTime;

	@Schema(description = "状态，0=未参与，1=参与，2=交卷")
	private Integer status;

	@Schema(description = "得分")
	private Integer score;

	@Schema(description = "是否完成批改")
	private Integer isCorrecting;


	@Schema(description = "考试开始时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date beginTime;

	@Schema(description = "考试结束时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date endTime;


	@Schema(description = "考试时限")
	private Integer timeLimit;

	@Schema(description = "考试名称")
	private String examName;



	@Schema(description = "得分")
	private Integer totalScore;

	@Schema(description = "预计考试结束时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date finishExamTime;



}