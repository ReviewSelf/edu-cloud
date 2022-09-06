package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;

import java.util.Date;

/**
* 问题卷关联问题表
*
* @author 樊磊 
* @since 1.0.0 2022-09-06
*/
@Data
@Schema(description = "问题卷关联问题表")
public class ProblemPaperItemVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "id")
	private Integer id;

	@Schema(description = "paperId")
	private Integer paperId;

	@Schema(description = "顺序")
	private Integer sort;

	@Schema(description = "题目id")
	private Integer problemId;

	@Schema(description = "分数")
	private Integer score;

	@Schema(description = "题目名称")
	private String problemName;

	@Schema(description = "题目类型")
	private Integer problemType;

	@Schema(description = "创建时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;


}