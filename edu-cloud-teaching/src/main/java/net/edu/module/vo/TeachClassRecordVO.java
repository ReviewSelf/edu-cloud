package net.edu.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
* 学生班级记录
*
* @author sqw 
* @since 1.0.0 2023-02-08
*/
@Data
@Schema(description = "学生班级记录")
public class TeachClassRecordVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "自增id")
	private Long id;

	@Schema(description = "1代表插班，2代表换班，3代表退班")
	private Integer type;

	@Schema(description = "记录时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date recordTime;

	@Schema(description = "学生id")
	private Long studentId;

	@Schema(description = "学生名称")
	private String studentName;

	@Schema(description = "教务老师id")
	private Long teacherId;

	@Schema(description = "教务老师名称")
	private String teacherName;

	@Schema(description = "原有班级id")
	private Long oldClassId;

	@Schema(description = "原班级名称")
	private String oldClassName;

	@Schema(description = "原有课堂id")
	private Long oldLessonId;

	@Schema(description = "新班级id")
	private Long newClassId;

	@Schema(description = "新班级名称")
	private String newClassName;

	@Schema(description = "新课堂id")
	private Long newLessonId;


}