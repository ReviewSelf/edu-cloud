package net.edu.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;


/**
* 课程总结
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-11-14
*/
@Data
@Schema(description = "课程总结")
public class ArchiveCourseSummaryVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "课程实施总结编号")
	private Long id;

	@Schema(description = "课程编号")
	private Long courseId;

	@Schema(description = "课程")
	private String courseName;

	@Schema(description = "问题分析（考核得分情况分析）")
	private String problemAnalysis;

	@Schema(description = "问题和改进措施（考核分析表（样本））")
	private String improvement;

	@Schema(description = "分析说明")
	private String analysisDescription;

	@Schema(description = "存在问题")
	private String problem;

	@Schema(description = "改进措施")
	private String measures;

	@Schema(description = "其他可用的协助持续改进的资源")
	private String resources;

	@Schema(description = "创建时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;

}
