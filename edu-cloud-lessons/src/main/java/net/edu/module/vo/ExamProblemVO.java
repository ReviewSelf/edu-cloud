package net.edu.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
* 课堂练习表
*
* @author 马佳浩 
* @since 1.0.0 2022-09-15
*/
@Data
@Schema(description = "课堂练习表")
public class ExamProblemVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "自增主键")
	private Long id;

	@Schema(description = "考试ID")
	private Long examId;

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

	@Schema(description = "来源，1=试卷（不可删除），2=老师添加（可删除）")
	private Integer source;



}