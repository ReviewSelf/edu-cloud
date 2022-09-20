package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;
import net.edu.framework.common.utils.DateUtils;
import java.util.Date;

/**
* 教学日历资源表
*
* @author 马佳浩 babamu@126.com
* @since 1.0.0 2022-09-15
*/
@Data
@Schema(description = "教学日历资源表")
public class LessonResourceVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@Schema(description = "名称")
	private String name;

	@Schema(description = "路径")
	private String filePath;
	@Schema(description = "备注")
	private String remark;

	@Schema(description = "资源类型，见字典表")
	private Integer fileType;

	@Schema(description = "来源，1=教学计划生成（不可删除），2=老师添加可删除")
	private Integer source;

	@Schema(description = "创建时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;


	@Schema(description = "更新时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date updateTime;


}