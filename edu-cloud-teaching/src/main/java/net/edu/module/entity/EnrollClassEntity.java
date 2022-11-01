package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.mybatis.entity.BaseEntity;

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
	 * 课程类型
	 */
	private String courseType;

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

	/**
	 * 结课日期
	 */
	private String classEnd;

	/**
	 * 价格
	 */
	private String price;
}
