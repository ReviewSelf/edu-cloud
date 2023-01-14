package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
* 能力课程
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-25
*/
@Data
@Schema(description = "能力课程")
public class ArchiveCourseVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "课程编号")
	private Long id;

	@Schema(description = "外部系统编号")
	private Long sysId;

	@Schema(description = "教学计划id")
	private Long teachPlanId;

	@Schema(description = "名称")
	private String name;

	@Schema(description = "上课时间")
	private String classCycle;

	@Schema(description = "学时")
	private Integer creditHours;

	@Schema(description = "周学时")
	private Integer weeklyCreditHours;

	@Schema(description = "课程类别")
	private String courseCategory;

	@Schema(description = "课程类型")
	private String type;

	@Schema(description = "开设学期")
	private String semester;

	@Schema(description = "年级")
	private String grade;


	@Schema(description = "上课地点")
	private String place;


	@Schema(description = "教学班级")
	private String teachClass;


	@Schema(description = "任课老师")
	private String teacher;


	@Schema(description = "学分")
	private String credit;


	@Schema(description = "考核方式")
	private String assessment;

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