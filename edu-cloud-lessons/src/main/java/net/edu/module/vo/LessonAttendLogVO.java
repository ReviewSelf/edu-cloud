package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;
import net.edu.framework.common.utils.DateUtils;
import java.util.Date;

/**
* 课堂签到表
*
* @author 马佳浩 
* @since 1.0.0 2022-09-15
*/
@Data
@Schema(description = "课堂签到表")
public class LessonAttendLogVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@Schema(description = "学生id")
	private Long stuId;

	@Schema(description = "课堂id")
	private Long lessonId;

	@Schema(description = "签到时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date checkinTime;

	@Schema(description = "课堂状态，0=请假，1=签到")
	private Integer status;

	@Schema(description = "是否点名")
	private Integer rollCall;

	@Schema(description = "创建时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;

	@Schema(description = "更新时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date updateTime;

	@Schema(description = "创建人")
	private Long creator;

	@Schema(description = "更新人")
	private Long updater;

	@Schema(description = "删除")
	private Integer deleted;

	@Schema(description = "版本")
	private Integer version;


}