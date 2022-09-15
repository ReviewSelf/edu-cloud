package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
* 课程表
*
* @author 翁瑞辰 babamu@126.com
* @since 1.0.0 2022-09-09
*/
@Data
@Schema(description = "课程表")
public class TeachLessonVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "课堂ID")
	private Long id;

	@Schema(description = "任课老师")
	private Long teacherId;

	@Schema(description = "班级ID")
	private Long classId;

	@Schema(description = "上课地点")
	private String place;

	@Schema(description = "预计开课时间")
	private String beginTime;

	@Schema(description = "预计结课时间")
	private String endTime;

	@Schema(description = "实际开始时间")
	private String startTime;

	@Schema(description = "实际结束时间")
	private String finishTime;

	@Schema(description = "创建时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;

	@Schema(description = "修改时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date updateTime;

	@Schema(description = "课堂状态，-1-未开启，0表示进行中，1-已结束，2-表示异常结束")
	private Integer state;

	@Schema(description = "创建人")
	private Long creator;

	@Schema(description = "更新人")
	private Long updater;

	@Schema(description = "版本")
	private Integer version;

	@Schema(description = "删除")
	private Integer deleted;


}