package net.edu.module.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import net.edu.framework.mybatis.entity.BaseEntity;

import java.util.Date;


/**
 * 课堂签到表
 *
 * @author 马佳浩 
 * @since 1.0.0 2022-09-15
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("lesson_attend_log")
public class LessonAttendLogEntity  {
	/**
	* 学生id
	*/
	private Long stuId;

	/**
	* 课堂id
	*/
	private Long lessonId;

	/**
	* 签到时间
	*/
	private Date checkinTime;

	/**
	* 课堂状态，0=请假，1=签到
	*/
	private Integer status;

	/**
	* 是否点名
	*/
	private Integer rollCall;

	/**
	 * 更新时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;


}