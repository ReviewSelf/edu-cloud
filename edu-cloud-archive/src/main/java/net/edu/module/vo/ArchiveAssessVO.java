package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
* 考核点
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-26
*/
@Data
@Schema(description = "考核点")
public class ArchiveAssessVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "考核点编号")
	private Long id;

	@Schema(description = "考核点名称")
	private String name;

	@Schema(description = "年级")
	private String grade;

	@Schema(description = "指标点编号")
	private Integer targetId;

	@Schema(description = "一级课程编号")
	private Integer firstKnowledgeId;

	@Schema(description = "删除")
	private Integer deleted;

	@Schema(description = "指标点名称")
	private String targetName;

	@Schema(description = "一级课程名称")
	private String firstKnowledgeName;

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


}
