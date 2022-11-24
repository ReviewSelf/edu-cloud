package net.edu.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
* 考试成绩表
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-11-16
*/
@Data
@Schema(description = "样本分析")
public class ArchiveGoalPeopleVO implements Serializable {
	//每个教学目标下的各项人数

	@Schema(description = "教学目标名称")
	private String targetName;

	@Schema(description = "优秀人数")
	private int excellent;

	@Schema(description = "良好人数")
	private int good;

	@Schema(description = "中等人数")
	private int medium;

	@Schema(description = "及格人数")
	private int pass;

	@Schema(description = "不及格人数")
	private int fail;

	@Schema(description = "目标值")
	private int target=1;

	@Schema(description = "评价值")
	private String evaluate;
}