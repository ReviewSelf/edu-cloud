package net.edu.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
* 问题卷表
*
* @author 樊磊 
* @since 1.0.0 2022-09-05
*/
@Data
@Schema(description = "问题卷表")
public class ProblemPaperVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	@Schema(description = "难度")
	private Integer difficulty;

	@Schema(description = "题目数量")
	private Integer problemNum;

	@Schema(description = "总分")
	private Integer score;
	@Schema(description = "说明")
	private String description;
	@Schema(description = "说明")
	private Integer kpId;
	@Schema(description = "知识点名称")
	private String kpName;
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;

	@Schema(description = "状态")
	private Integer status;

	@Schema(description = "创建人")
	private Long creator;




}