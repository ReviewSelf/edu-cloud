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
	* 状态,0没有发布，1已经发布
	*/
	private Integer status;

	/**
	* 班级名称
	*/
	private String className;

	/**
	* 开始时间
	*/
	private String startTime;

	/**
	* 结束时间
	*/
	private String endTime;

	/**
	* 面向对象
	*/
	private String object;

	/**
	* 课程目标
	*/
	private String target;


	/**
	* 上课日期
	*/
	private String classDate;

}