package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
* 课程表
*
* @author 马佳浩
* @since 1.0.0 2022-09-15
*/
@Data
@Schema(description = "课程表")
public class LessonVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "课堂ID")
	private Long id;

	@Schema(description = "日历id")
	private Long planItemId;

	@Schema(description = "日历名称")
	private String planItemName;

	@Schema(description = "任课老师")
	private Long teacherId;
	@Schema(description = "任课老师")
	private String teacherName;


	@Schema(description = "班级ID")
	private Long classId;

	@Schema(description = "班级名称")
	private String className;

	@Schema(description = "名称")
	private String name;

	@Schema(description = "上课地点")
	private String place;

	@Schema(description = "预计开课时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date beginTime;

	@Schema(description = "预计结课时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date endTime;

	@Schema(description = "作业开始时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date homeworkBeginTime;

	@Schema(description = "作业结束时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date homeworkEndTime;

	@Schema(description = "作业状态， 0：无，1：有 ，2：结束")
	private Integer homeworkStatus;

	@Schema(description = "创建时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;

	@Schema(description = "修改时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date updateTime;

	@Schema(description = "课堂状态，-1-未开启，0表示进行中，1-已结束，2-表示异常结束")
	private Integer status;

	@Schema(description = "创建人")
	private Long creator;

	@Schema(description = "更新人")
	private Long updater;


	@Schema(description = "删除")
	private Integer deleted;

	@Schema(description = "顺序")
	private Integer sort;

	@Schema(description = "顺序")
	private String description;

	@Schema(description = "版本")
	private Integer version;
}
