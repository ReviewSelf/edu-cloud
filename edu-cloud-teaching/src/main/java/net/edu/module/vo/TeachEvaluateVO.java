package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;
import net.edu.framework.common.utils.DateUtils;
import java.util.Date;

/**
* 教学评价
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-08
*/
@Data
@Schema(description = "教学评价")
public class TeachEvaluateVO{
	private static final long serialVersionUID = 1L;

	@Schema(description = "id")
	private Long id;

	@Schema(description = "评价数据id")
	private Long stuId;

	@Schema(description = "班级名称")
	private String className;


	@Schema(description = "学生姓名")
	private String name;

	@Schema(description = "课堂名称")
	private String lessonName;

	@Schema(description = "评价至系统")
	private String sysEvaluation ;

	@Schema(description = "评价至老师")
	private Long teaEvaluation;


}