package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
* 一级知识点权重
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-29
*/
@Data
@Schema(description = "一级知识点权重")
public class ArchiveWeightTargetCourseVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "支撑矩阵编号")
	private Long id;

	@Schema(description = "指标点编号")
	private Long targetId;

	@Schema(description = "一级知识点编号")
	private Long courseId;

	@Schema(description = "一级知识点名称")
	private String courseName;

	@Schema(description = "权重")
	private Double weight;

	@Schema(description = "删除")
	private Integer deleted;

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