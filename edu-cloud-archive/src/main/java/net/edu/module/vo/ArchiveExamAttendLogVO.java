package net.edu.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
* 123
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-11-11
*/
@Data
@Schema(description = "123")
public class ArchiveExamAttendLogVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "自增主键")
	private Long id;

	@Schema(description = "用户id")
	private Long userId;

	@Schema(description = "学号")
	private Long userNumber;

	@Schema(description = "用户姓名")
	private String userName;

	@Schema(description = "考试id")
	private Long examId;

	@Schema(description = "开始时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date joinTime;

	@Schema(description = "结束时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date quitTime;

	@Schema(description = "得分")
	private Integer score;

	@Schema(description = "状态")
	private Integer status;

	@Schema(description = "是否批改完成")
	private Integer isCorrecting;



}