package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;
import net.edu.framework.common.utils.DateUtils;
import java.util.Date;

/**
* 教学评价
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-08
*/
@Data
@Schema(description = "教学评价")
public class TeachEvaluateVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "评价数据id")
	private Long id;

	@Schema(description = "评价人id")
	private Long evaluateUserId;

	@Schema(description = "评价人名字")
	private String evaluateUserName;

	@Schema(description = "被评价人id")
	private Integer evaluatedUserId;

	@Schema(description = "被评价人名字")
	private String evaluatedUserName;

	@Schema(description = "评价得分")
	private Integer score;

	@Schema(description = "班级id")
	private Integer classId;

	@Schema(description = "班级名字")
	private String className;

	@Schema(description = "创建时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;

	@Schema(description = "是否被删除")
	private String deleted;

	@Schema(description = "类型1：老师评价学生，2：学生评价老师")
	private Integer type;

	@Schema(description = "评价内容")
	private String content;


}