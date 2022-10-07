package net.edu.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
* 教学计划表
*
* @author sqw 
* @since 1.0.0 2022-09-12
*/
@Data
@Schema(description = "教学计划表")
public class TeachPlanVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "id")
	private Long id;

	@Schema(description = "名称")
	private String name;

	@Schema(description = "说明")
	private String description;

	@Schema(description = "难度")
	private Integer difficulty;

	@Schema(description = "课次")
	private Integer lessonNum;

	@Schema(description = "引用次数")
	private Integer usedNum;


	@Schema(description = "1=启用，2=停用")
	private Integer status;

	@Schema(description = "创建时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;


}