package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Data;
import java.io.Serializable;

import net.edu.framework.common.utils.DateUtils;
import java.util.Date;
import java.util.List;

/**
* 考试
*
* @author 小樊 babamu@126.com
* @since 1.0.0 2022-10-09
*/
@Data
@Schema(description = "考试")
public class ExamVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "自增主键")
	private Long id;

	@Schema(description = "试卷ID")
	private Long paperId;

	@Schema(description = "题目数量")
	private Integer problemNum;

	@Schema(description = "考试说明")
	private String description;

	@Schema(description = "开始时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date beginTime;

	@Schema(description = "结束时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date endTime;

	@Schema(description = "总分")
	private Integer score;

	@Schema(description = "监考老师")
	private Long teacherId;

	@Schema(description = "知识点代码")
	private String kpCode;


	@Schema(description = "考试名称")
	private String name;

	@Schema(description = "考试地点")
	private String place;

	@Schema(description = "时间限制")
	private Integer timeLimit;

	@Schema(description = "学生id")
	private Long userId;

	@Schema(description = "监考老师名字")
	private String teacherName;

	@Schema(description = "参加状态")
	private Integer status;
	@Schema(description = "是否批改")
	private Integer  isCorrecting;

	@Schema(description = "班级名字")
	private List<String> classListName;

	@Schema(description = "分数")
	private Integer  grade;


	@Schema(description = "班级ID")
	private List<Long> classIdList;

	@Schema(description = "备注")
	private String remark;

	@Schema(description = "考试类型")
	private Integer type;

	@Schema(description = "能力考试结果")
	private Integer result;

	@Schema(description = "能力等级门槛id")
	private Integer requireAbility;

	@Schema(description = "获取能力等级id")
	private Integer gainAbilityId;

	@Schema(description = "能力等级门槛名称")
	private String requireAbilityName;

	@Schema(description = "获取能力等级名称")
	private String gainAbilityName;

	@Schema(description = "考试题目是否乱序")
	private Integer problemOrder;

	@Schema(description = "考试过程中是否可以返回上一题")
	private Integer problemBack;

}
