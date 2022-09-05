package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;
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

	private Long id;

	@Schema(description = "名称")
	private String name;

	@Schema(description = "说明")
	private String description;

	@Schema(description = "图片")
	private String img;

	@Schema(description = "来源")
	private String source;

	@Schema(description = "提示")
	private String tips;

	@Schema(description = "建议用时(分)")
	private Integer adviceTime;

	@Schema(description = "难度")
	private Integer difficulty;

	@Schema(description = "输入说明")
	private String inputExplain;

	@Schema(description = "输出说明")
	private String outputExplain;

	@Schema(description = "样例输入")
	private String sampleInput;

	@Schema(description = "样例输出")
	private String sampleOutput;

	@Schema(description = "典型问题")
	private Integer isTypical;

	@Schema(description = "测试样例数")
	private Integer sampleNum;

	@Schema(description = "知识点id")
	private Integer kpId;

	@Schema(description = "提交次数")
	private Integer submitTimes;

	@Schema(description = "正确次数")
	private Integer correctTimes;

	@Schema(description = "引用次数")
	private Integer usedNum;

	@Schema(description = "创建时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;

	@Schema(description = "更新时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date updateTime;

	@Schema(description = "更新人")
	private Integer updater;

	@Schema(description = "创建人")
	private Integer creator;

	@Schema(description = "是否删除")
	private Integer deleted;

	@Schema(description = "状态")
	private Integer status;

	@Schema(description = "空间限制(KB)")
	private Integer memoryLimit;

	@Schema(description = "时间限制")
	private Integer timeLimit;


}