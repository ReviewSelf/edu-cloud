package net.edu.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;


import java.io.Serializable;
import java.util.Date;

/**
* 沟通记录表
*
* @author sqw 
* @since 1.0.0 2023-02-05
*/
@Data
@Schema(description = "沟通记录表")
public class TeachCommunicateRecordVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "自增id")
	private Long id;

	@Schema(description = "教务老师id")
	private Long teacherId;

	@Schema(description = "学生id")
	private Long studentId;

	@Schema(description = "沟通对象")
	private String target;

	@Schema(description = "沟通时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date time;

	@Schema(description = "沟通主题")
	private String theme;

	@Schema(description = "沟通内容")
	private String content;

	@Schema(description = "创建时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;

	@Schema(description = "更新时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date updateTime;

	@Schema(description = "创建人")
	private Long creator;

	@Schema(description = "更新人")
	private Long updater;

	@Schema(description = "删除")
	private Integer deleted;

	@Schema(description = "版本")
	private Integer version;


}