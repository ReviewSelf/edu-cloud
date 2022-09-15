package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;

import java.util.Date;

/**
* 1
*
* @author 马佳浩 
* @since 1.0.0 2022-09-15
*/
@Data
@Schema(description = "1")
public class LessonIPVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@Schema(description = "课堂id")
	private Long lessonId;

	@Schema(description = "IP段")
	private String ipRange;

	@Schema(description = "备注")
	private String remark;

	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;

	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date updateTime;

	private Long creator;

	private Long updater;

	private Integer version;

	private Integer deleted;


}