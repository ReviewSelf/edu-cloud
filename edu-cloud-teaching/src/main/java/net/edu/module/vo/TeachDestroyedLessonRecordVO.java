package net.edu.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;
import org.apache.poi.hpsf.Decimal;

import java.io.Serializable;
import java.util.Date;

/**
* 消课管理
*
* @author sqw 
* @since 1.0.0 2023-03-04
*/
@Data
@Schema(description = "消课管理")
public class TeachDestroyedLessonRecordVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "id")
	private Long id;

	@Schema(description = "学生id")
	private Integer stuId;

	@Schema(description = "学生姓名")
	private String studentName;

	@Schema(description = "班级id")
	private Integer classId;

	@Schema(description = "班级名称")
	private String className;

	@Schema(description = "课堂id")
	private Integer lessonId;

	@Schema(description = "课堂名称")
	private Integer lessonName;

	@Schema(description = "学生课堂签到情况")
	private Integer studentLessonStatus;

	@Schema(description = "状态1=已扣费0=未扣费")
	private Integer status;

	@Schema(description = "时长")
	private Decimal duration;

	@Schema(description = "实际扣时")
	private Decimal actualDeduction;

	@Schema(description = "说明")
	private String direction;

	@Schema(description = "类型0=常规课1=集训课")
	private Integer type;

	@Schema(description = "版本号")
	private Integer version;

	@Schema(description = "删除标识  0：正常   1：已删除")
	private Integer deleted;

	@Schema(description = "创建者")
	private Long creator;

	@Schema(description = "创建时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;

	@Schema(description = "更新者")
	private Long updater;

	@Schema(description = "更新时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date updateTime;


}