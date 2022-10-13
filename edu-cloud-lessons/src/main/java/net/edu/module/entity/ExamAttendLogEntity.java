package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.utils.DateUtils;

import java.util.Date;


/**
 * 课堂签到表
 *
 * @author 马佳浩 
 * @since 1.0.0 2022-09-15
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("exam_attend_log")
public class ExamAttendLogEntity {
	@TableId
	private Long id;

	@Schema(description = "学生id")
	private Long userId;

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

	@Schema(description = "创建时间")
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;

	@Schema(description = "更新时间")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;


}