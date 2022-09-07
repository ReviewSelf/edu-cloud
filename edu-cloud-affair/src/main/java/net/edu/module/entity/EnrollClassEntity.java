package net.edu.module.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import net.edu.framework.mybatis.entity.BaseEntity;

import java.util.Date;

/**
 * 班级发布
 *
 * @author 翁瑞辰 
 * @since  2022-09-06
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("enroll_class")
public class EnrollClassEntity extends BaseEntity {




	/**
	* 创建人id
	*/
	private Integer createBy;

	/**
	* 0没有发布，1已经发布
	*/
	private Integer status;

	/**
	* 班级名称
	*/
	private String className;

	/**
	* 开始时间
	*/
	private Date startTime;

	/**
	* 结束时间
	*/
	private Date endTime;

	/**
	* 面向对象
	*/
	private String object;

	/**
	* 课程目标
	*/
	private String target;

	/**
	* 是否删除
	*/
	@TableLogic
	@TableField(fill = FieldFill.INSERT)
	private Integer deleted;

	/**
	* 上课日期
	*/
	private Date classDate;

}