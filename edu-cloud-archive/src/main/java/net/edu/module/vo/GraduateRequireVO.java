package net.edu.module.vo;

import com.alibaba.excel.annotation.ExcelProperty;
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

	@ExcelProperty(index = 0)
	@Schema(description = "标题")
	private String title;

	@ExcelProperty(index = 1)
	@Schema(description = "描述")
	private String content;

	@ExcelProperty(index = 5)
	@Schema(description = "年份")
	private String grade;

	@ExcelProperty(index = 2)
	@Schema(description = "专业")
	private Long major;

	@Schema(description = "专业名称")
	private String majorName;

	@ExcelProperty(index = 3)
	@Schema(description = "学院")
	private Long college;

	@Schema(description = "学院名称")
	private String collegeName;

	@Schema(description = "学校名称")
	private String schoolName;

	@ExcelProperty(index = 4)
	@Schema(description = "学校")
	private Long school;

	@Schema(description = "指标点名称")
	private String targetName;

	@Schema(description = "课程名称")
	private String courseName;

	@Schema(description = "权重")
	private String weight;

	@Schema(description = "排序")
	private Integer sort;

}
