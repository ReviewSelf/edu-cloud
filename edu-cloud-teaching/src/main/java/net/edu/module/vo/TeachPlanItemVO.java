package net.edu.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
* 教学日历表
*
* @author sqw 
* @since 1.0.0 2022-09-12
*/
@Data
@Schema(description = "教学日历表")
public class TeachPlanItemVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "计划子项")
	private Long id;

	@Schema(description = "教学计划id")
	private Long planId;

	@Schema(description = "子项顺序")
	private Integer sort;

	@Schema(description = "时长（分钟）")
	private Integer duration;

	@Schema(description = "名称")
	private String name;

	@Schema(description = "说明")
	private String description;



}