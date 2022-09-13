package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.mybatis.entity.BaseEntity;

import java.util.Date;

/**
 * 教学计划表
 *
 * @author sqw 
 * @since 1.0.0 2022-09-12
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("teach_plan")
public class TeachPlanEntity extends BaseEntity {

	/**
	* 名称
	*/
	private String name;

	/**
	* 说明
	*/
	private String description;

	/**
	* 难度
	*/
	private Integer difficulty;

	/**
	* 课次
	*/
	private Integer lessonNum;

	/**
	* 引用次数
	*/
	private Integer usedNum;

	/**
	* 1=启用，2=停用
	*/
	private Integer status;



}