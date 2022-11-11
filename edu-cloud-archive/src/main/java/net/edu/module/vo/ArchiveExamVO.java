package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;
import net.edu.framework.common.utils.DateUtils;
import java.util.Date;

/**
* 123
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-11-11
*/
@Data
@Schema(description = "123")
public class ArchiveExamVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "自增主键")
	private Long id;

	@Schema(description = "试卷ID")
	private Long paperId;

	@Schema(description = "试卷名称")
	private String paperName;

	@Schema(description = "考试名称")
	private String name;

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

	@Schema(description = "监考老师id")
	private Long teacherId;

	@Schema(description = "监考老师")
	private String teacherName;

	@Schema(description = "班级名称")
	private String className;

	@Schema(description = "知识点ID")
	private String kpCode;

	@Schema(description = "知识点")
	private String kpName;

	@Schema(description = "创建时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;

	@Schema(description = "版本")
	private Integer version;

	@Schema(description = "修改时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date updateTime;

	@Schema(description = "创建人")
	private Long creator;

	@Schema(description = "更新人")
	private Long updater;

	@Schema(description = "删除")
	private Integer deleted;

	@Schema(description = "考试地点")
	private String place;

	@Schema(description = "考试时长")
	private Integer timeLimit;

	@Schema(description = "班级id")
	private Long classId;

	@Schema(description = "题目数量")
	private Integer problemNum;


}