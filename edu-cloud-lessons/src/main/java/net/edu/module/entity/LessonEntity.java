package net.edu.module.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import net.edu.framework.common.utils.DateUtils;

import java.util.Date;

/**
 * 课程表
 *
 * @author 马佳浩
 * @since 1.0.0 2022-09-15
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("lesson")
public class LessonEntity {
	/**
	* 课堂ID
	*/
	@TableId
	private Long id;

	/**
	* 任课老师
	*/
	private Long teacherId;

	/**
	 * 日历id
	 */
	private Long planItemId;

	/**
	* 班级ID
	*/
	private Long classId;

	/**
	* 上课地点
	*/
	private String place;

	/**
	 * 名称
	 */
	private String name;

	/**
	* 开课时间
	*/
	private Date beginTime;

	/**
	* 结课时间
	*/
	private Date endTime;

	/**
	 * 作业开始时间
	 */
	private Date homeworkBeginTime;

	/**
	 * 作业结束时间
	 */
	private Date homeworkEndTime;

	/**
	 * 作业状态， 0：无，1：有 ，2：结束
	 */
	private Integer homeworkStatus;

	/**
	* 创建时间
	*/
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;

	/**
	* 修改时间
	*/
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

	/**
	* 课堂状态，-1-未开启，0表示进行中，1-已结束，2-表示异常结束(弃用)
	*/
	private Integer status;

	/**
	* 创建人
	*/
	@TableField(fill = FieldFill.INSERT)
	private Long creator;

	/**
	* 更新人
	*/
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Long updater;

	/**
	* 版本
	*/
	private Integer version;

	/**
	* 删除
	*/
	@TableLogic
	@TableField(fill = FieldFill.INSERT)
	private Integer deleted;


	private Integer sort;

	private String description;
}
