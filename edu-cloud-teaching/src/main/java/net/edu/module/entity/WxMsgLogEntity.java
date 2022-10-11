package net.edu.module.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;

/**
 * 消息推送
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-11
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("wx_msg_log")
public class WxMsgLogEntity {
	/**
	* 消息编号
	*/
	@TableId
	private Integer id;

	/**
	* 内容
	*/
	private String content;

	/**
	* 创建时间
	*/
	@TableField(fill = FieldFill.INSERT)
	private String createTime;

	/**
	* 发送时间
	*/
	private String sendTime;

	/**
	* 已经发送时间
	*/
	private String sentTime;

	/**
	* 来源
	*/
	private String source;

	/**
	* 面向用户
	*/
	private Long userId;

	/**
	 * 用户姓名
	 */
	private String realName;

	/**
	* 用户类型
	*/
	private Integer userType;

	/**
	* 是否已推
	*/
	private Integer isPush;

	/**
	* 是否接収
	*/
	private Integer isReceive;

	/**
	* 消息类型
	*/
	private Integer type;

	/**
	* 模板ID
	*/
	private String templateId;

}