package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;


/**
* 毕业要求
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-20
*/
@Data
@Schema(description = "毕业要求")
public class GraduateRequireVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "毕业要求id")
	private Long id;

	@Schema(description = "标题")
	private String title;

	@Schema(description = "描述")
	private String content;

	@Schema(description = "专业")
	private Long major;

	@Schema(description = "专业名称")
	private String majorName;

	@Schema(description = "学院")
	private Long college;

	@Schema(description = "学院名称")
	private String collegeName;

	@Schema(description = "学校名称")
	private String schoolName;

	@Schema(description = "学校")
	private Long school;


}
