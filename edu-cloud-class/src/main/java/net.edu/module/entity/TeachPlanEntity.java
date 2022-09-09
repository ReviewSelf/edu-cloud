package net.edu.module.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import net.edu.framework.mybatis.entity.BaseEntity;


/**
 * 教学计划表
 *
 * @author 阿沐
 * @since 1.0.0 2022-09-08
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
	* 内容
	*/
	private String content;

	/**
	* 难度
	*/
	private Integer difficulty;

	/**
	* 课时
	*/
	private Integer lessonNum;

	/**
	* 引用次数
	*/
	private Integer usedNum;

	/**
	* 1=启用，2=停用
	*/
	private Integer state;

}
