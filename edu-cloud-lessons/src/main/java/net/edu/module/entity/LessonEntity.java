package net.edu.module.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
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
	* 班级ID
	*/
	private Long classId;

	/**
	* 上课地点('0'表示翠柏校区，'1'表示联盛校区)
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

}