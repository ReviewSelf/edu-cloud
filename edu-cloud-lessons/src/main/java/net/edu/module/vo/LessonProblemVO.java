package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;
import net.edu.framework.common.utils.DateUtils;
import java.util.Date;

/**
* 课堂练习表
*
* @author 马佳浩 
* @since 1.0.0 2022-09-15
*/
@Data
@Schema(description = "课堂练习表")
public class LessonProblemVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "自增主键")
	private Long id;

	@Schema(description = "课堂ID")
	private Long lessonId;

	@Schema(description = "分数")
	private Integer score;

	@Schema(description = "顺序")
	private Integer sort;

	@Schema(description = "问题ID")
	private Long problemId;

	@Schema(description = "问题名称（冗余，提高效率）")
	private String problemName;

	@Schema(description = "问题类型(选择填空代码)")
	private Integer problemType;

	@Schema(description = "开始时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date beginTime;

	@Schema(description = "结束时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date endTime;

	@Schema(description = "来源，1=教学计划（不可删除），2=老师添加（可删除）")
	private Integer source;

	@Schema(description = "类型（课前，课中，课后）")
	private Integer type;



}