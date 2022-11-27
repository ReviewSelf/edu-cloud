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
@Schema(description = "考试成绩表")
public class ArchiveGoalScoreVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "自增id")
	private Long id;

	@Schema(description = "学号")
	private String stuId;

	@Schema(description = "得分")
	private List<Double> score;

	@Schema(description = "总分")
	private Double total;

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

	@Schema(description = "学生姓名")
	private String stuName;

	@Schema(description = "评测id")
	private Long courseId;

	@Schema(description = "平均达成度")
	private Double[] avg;

	@Schema(description = "权重")
	private List<Double> weights;

}
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: TODO
 * @author: sl
 * @date: 2022年11月26日 21:08
 */
@Data
@Schema(description = "记分册成绩")
public class ArchiveGoalScoreVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String majorName;
    private String className;
    private String targetNum;
    private String actualNum;
    private String absentNum;
    private String excellent;
    private String excellentPercent;
    private String good;
    private String goodPercent;
    private String medium;
    private String mediumPercent;
    private String pass;
    private String passPercent;
    private String fail;
    private String failPercent;

}
