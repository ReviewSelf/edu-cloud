package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;

import net.edu.framework.common.utils.DateUtils;
import java.util.Date;

/**
* 记分册
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-11-22
*/
@Data
@Schema(description = "记分册")
public class ArchiveScoreBookVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "编号")
	private Long id;

	@Schema(description = "教学记事")
	private String teachingNotes;

	@Schema(description = "授课班级名称")
	private String className;

	@Schema(description = "教师")
	private String teacherName;

	@Schema(description = "教研系")
	private String majorName;

	@Schema(description = "课程表")
	private String classSchedule;

	@Schema(description = "删除")
	private Integer deleted;

	@Schema(description = "创建时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;

	@Schema(description = "修改时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date updateTime;

	@Schema(description = "创建人")
	private String creator;

	@Schema(description = "版本号")
	private Integer version;

	@Schema(description = "修改人")
	private String updater;

	@Schema(description = "课程id")
	private Long courseId;

	@Schema(description = "课程总结Id")
	private Long summaryId;

	@Schema(description = "辅导答疑")
	private String answerNotes;



}
