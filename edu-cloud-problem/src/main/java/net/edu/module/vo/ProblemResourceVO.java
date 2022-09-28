package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
* 问题资源表
*
* @author 周建超 
* @since 1.0.0 2022-09-20
*/
@Data
@Schema(description = "问题资源表")
public class ProblemResourceVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@Schema(description = "问题ID")
	private Long problemId;

	@Schema(description = "问题类型 1选择 2填空 3代码")
	private Integer problemType;

	@Schema(description = "文件路径")
	private String filePath;

	@Schema(description = "文件类型")
	private String fileType;

	@Schema(description = "创建时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;

	@Schema(description = "删除")
	private String deleted;

	@Schema(description = "资源备注")
	private String remark;


}