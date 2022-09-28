package net.edu.module.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
* 填空题表
*
* @author 马佳浩 
* @since 1.0.0 2022-09-05
*/
@Data
@Schema(description = "填空题表")
public class FillProblemVO implements Serializable {
	private static final long serialVersionUID = 1L;
	@ExcelIgnore
	private Integer id;

	@Schema(description = "名称")
	@ExcelProperty(index = 0)
	private String name;

	@Schema(description = "说明")
	@ExcelProperty(index = 1)
	private String description;

	@Schema(description = "来源")
	@ExcelProperty(index = 2)
	private String source;

	@Schema(description = "提示")
	@ExcelProperty(index = 3)
	private String tips;

	@Schema(description = "建议用时")
	@ExcelProperty(index = 4)
	private Integer adviceTime;

	@Schema(description = "知识点代码")
	@ExcelProperty(index = 5)
	private String kpCode;

	@Schema(description = "图片")
	@ExcelProperty(index = 6)
	private String img;

	@Schema(description = "难度")
	@ExcelProperty(index = 7)
	private Integer difficulty;




	@Schema(description = "知识点名称")
	@ExcelIgnore
	private String kpName;

	@Schema(description = "提交次数")
	@ExcelIgnore
	private Integer submitTimes;

	@Schema(description = "正确次数")
	@ExcelIgnore
	private Integer correctTimes;

	@Schema(description = "典型问题")
	@ExcelProperty(index = 8)
	private Integer isTypical;

	@Schema(description = "引用次数")
	@ExcelIgnore
	private Integer usedNum;


	@Schema(description = "参考答案")
	@ExcelProperty(index = 9)
	private String answer;

	@Schema(description = "创建时间")
	@ExcelIgnore
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;


	@Schema(description = "创建人")
	@ExcelIgnore
	private Integer creator;


	@Schema(description = "状态")
	@ExcelIgnore
	private Integer status;


}