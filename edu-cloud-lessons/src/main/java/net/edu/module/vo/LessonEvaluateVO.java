package net.edu.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.edu.framework.common.utils.DateUtils;
import org.checkerframework.checker.units.qual.A;

import java.io.Serializable;
import java.util.Date;

/**
* 课堂评价
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-18
*/
@Data
@Schema(description = "课堂评价")
@NoArgsConstructor
@AllArgsConstructor
public class LessonEvaluateVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@Schema(description = "学生id")
	private Long userId;

	@Schema(description = "课堂id")
	private Long lessonId;

	@Schema(description = "名次")
	private Integer rankNum;

	@Schema(description = "未答题")
	private Integer unansweredNum;

	@Schema(description = "答题量")
	private Integer answeredNum;

	@Schema(description = "未判量")
	private Integer undecidedNum;

	@Schema(description = "正确量")
	private Integer correctNum;

	@Schema(description = "错题量")
	private Integer errorNum;

	@Schema(description = "评价内容")
	private String content;

	@Schema(description = "评价人")
	private String name;


}