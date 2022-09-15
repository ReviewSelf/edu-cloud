package net.edu.module.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;

/**
 * 课程表
 *
 * @author 翁瑞辰 babamu@126.com
 * @since 1.0.0 2022-09-09
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("teach_lesson")
public class TeachLessonEntity {
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
	* 班级ID
	*/
	private Long classId;

	/**
	* 上课地点
	*/
	private String place;

	/**
	* 预计开课时间
	*/
	private String beginTime;

	/**
	* 预计结课时间
	*/
	private String endTime;

	/**
	* 实际开始时间
	*/
	private String startTime;

	/**
	* 实际结束时间
	*/
	private String finishTime;

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
	* 课堂状态，-1-未开启，0表示进行中，1-已结束，2-表示异常结束
	*/
	private Integer state;

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

}