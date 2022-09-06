package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.mybatis.entity.BaseEntity;

/**
 * 问题卷表
 *
 * @author 樊磊 
 * @since 1.0.0 2022-09-05
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("problem_paper")
public class ProblemPaperEntity extends BaseEntity {
	private String name;

	/**
	* 难度
	*/
	private Integer difficulty;

	/**
	* 题目数量
	*/
	private Integer problemNum;

	/**
	* 总分
	*/
	private Integer score;

	private String description;

	/**
	* 状态
	*/
	private Integer status;

}