package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.mybatis.entity.BaseEntity;

import java.util.Date;

/**
 * 教学日历表
 *
 * @author sqw 
 * @since 1.0.0 2022-09-12
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("teach_plan_item")
public class TeachPlanItemEntity extends BaseEntity {

	/**
	* 教学计划id
	*/
	private Long planId;

	/**
	* 子项顺序
	*/
	private Integer sort;

	/**
	* 时长（分钟）
	*/
	private Integer duration;

	/**
	* 名称
	*/
	private String name;

	/**
	* 说明
	*/
	private String description;


}