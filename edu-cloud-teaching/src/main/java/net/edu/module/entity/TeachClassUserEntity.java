package net.edu.module.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;

/**
 * 班级用户表
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-09-16
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("teach_class_user")
public class TeachClassUserEntity {
	/**
	* 自增主键
	*/
	@TableId
	private Long id;

	/**
	* 班级id
	*/
	private Long classId;

	/**
	* 用户id
	*/
	private Long userId;

	/**
	* 加入课次
	*/
	private Integer joinLessonTimes;

	/**
	* 退出课次
	*/
	private Integer quitLessonTimes;

	/**
	* 加入时间
	*/
	private Date joinTime;

	/**
	* 退出时间
	*/
	private Date quitTime;

	/**
	* 1=正常，2=退班
	*/
	private Integer status;

	/**
	* 迟到次数
	*/
	private Integer lateTimes;

	/**
	* 作业按时提交次数(每次作业截止时统计。)
	*/
	private Integer homeworkTimes;

	/**
	* 作业未完成题目总数量(每次作业截止时统计。)
	*/
	private Integer unfinishedProblemNum;

	/**
	* 缴费金额
	*/
	private String paymentAmount;

	/**
	* 缴费时间
	*/
	private Date paymentTimes;

	/**
	* 实际金额
	*/
	private String paymentTrue;

	/**
	* 创建时间
	*/
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;

	/**
	* 更新时间
	*/
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

	/**
	* 逻辑删除
	*/
	@TableLogic
	@TableField(fill = FieldFill.INSERT)
	private Integer deleted;

	/**
	* 更新人
	*/
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Long updater;

	/**
	* 创建人
	*/
	@TableField(fill = FieldFill.INSERT)
	private Long creator;

	/**
	* 版本
	*/
	private Integer version;

}