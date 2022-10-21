package net.edu.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
* 消息推送
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-11
*/
@Data
@Schema(description = "消息推送")
public class WxMsgLogVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "消息编号")
	private Integer id;

	@Schema(description = "内容")
	private String content;

	@Schema(description = "创建时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;

	@Schema(description = "发送时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date sendTime;

	@Schema(description = "已经发送时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date sentTime;

	@Schema(description = "来源")
	private String source;

	@Schema(description = "面向用户")
	private Long userId;

	@Schema(description = "用户类型")
	private Integer userType;

	@Schema(description = "是否已推")
	private Integer isPush;

	@Schema(description = "是否接収")
	private Integer isReceive;

	@Schema(description = "消息类型")
	private Integer type;

	@Schema(description = "模板ID")
	private String templateId;


}
