package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
* 平时记录
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-11-23
*/
@Data
@Schema(description = "平时记录")
public class ArchiveSignVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "签到表记录编号")
	private Long id;

	@Schema(description = "签到记录")
	private String record;

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

	@Schema(description = "学号")
	private String stuId;

	@Schema(description = "学生姓名")
	private String stuName;

	@Schema(description = "记分册编号")
	private Long bookId;


}
