package net.edu.module.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
* 代码题库表
*
* @author 马佳浩 
* @since 1.0.0 2022-09-05
*/
@Data
@Schema(description = "代码题库表")
public class CodeProblemVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@ExcelIgnore
	private Long id;

	@Schema(description = "名称")
	@ExcelProperty(index = 0)
	private String name;

	@Schema(description = "说明")
	@ExcelProperty(index = 1)
	private String description;

	@Schema(description = "图片")
	@ExcelProperty(index = 2)
	private String img;

	@Schema(description = "来源")
	@ExcelProperty(index = 3)
	private String source;

	@Schema(description = "提示")
	@ExcelProperty(index = 4)
	private String tips;

	@Schema(description = "建议用时(分)")
	@ExcelProperty(index = 5)
	private Integer adviceTime;

	@Schema(description = "知识点代码")
	@ExcelProperty(index = 6)
	private String kpCode;

	@Schema(description = "难度")
	@ExcelProperty(index = 7)
	private Integer difficulty;

	@Schema(description = "输入说明")
	@ExcelProperty(index = 8)
	private String inputExplain;

	@Schema(description = "输出说明")
	@ExcelProperty(index = 9)
	private String outputExplain;

	@Schema(description = "样例输入")
	@ExcelProperty(index = 10)
	private String sampleInput;

	@Schema(description = "样例输出")
	@ExcelProperty(index = 11)
	private String sampleOutput;

	@Schema(description = "典型问题")
	@ExcelProperty(index = 12)
	private Integer isTypical;

	@Schema(description = "测试样例数")
	@ExcelIgnore
	private Integer sampleNum;



	@Schema(description = "知识点名称")
	@ExcelIgnore
	private String kpName;

	@Schema(description = "提交次数")
	@ExcelIgnore
	private Integer submitTimes;

	@Schema(description = "正确次数")
	@ExcelIgnore
	private Integer correctTimes;

	@Schema(description = "引用次数")
	@ExcelIgnore
	private Integer usedNum;


	@Schema(description = "空间限制(MB)")
	@Min(value = 2, message = "值不能小于2MB")
	@ExcelProperty(index = 13)
	private Integer memoryLimit;

	@Schema(description = "时间限制")
	@Min(value = 0, message = "至少0s")
	@Max(value = 15, message = "值不能超过15s")
	@ExcelProperty(index = 14)
	private BigDecimal timeLimit;

	@Schema(description = "创建时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	@ExcelIgnore
	private Date createTime;


	@Schema(description = "创建人")
	@ExcelIgnore
	private Integer creator;

	@Schema(description = "状态")
	@ExcelIgnore
	private Integer status;

	@Schema(description = "参考答案")
	@ExcelProperty(index = 15)
	private String answer;
}