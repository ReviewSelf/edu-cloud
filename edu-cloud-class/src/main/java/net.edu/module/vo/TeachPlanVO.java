package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
* 教学计划表
*
* @author 阿沐
* @since 1.0.0 2022-09-08
*/
@Data
@Schema(description = "教学计划表")
public class TeachPlanVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "教学计划ID")
	private Integer id;

	@Schema(description = "名称")
	private String name;

	@Schema(description = "内容")
	private String content;

	@Schema(description = "难度")
	private Integer difficulty;

	@Schema(description = "课时")
	private Integer lessonNum;

	@Schema(description = "引用次数")
	private Integer usedNum;

	@Schema(description = "创建时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;

	@Schema(description = "更新时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date updateTime;

	@Schema(description = "1=启用，2=停用")
	private Integer state;

	@Schema(description = "是否删除")
	private Integer deleted;


}
