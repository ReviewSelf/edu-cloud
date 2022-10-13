package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 课堂练习表
 *
 * @author 马佳浩 
 * @since 1.0.0 2022-09-15
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("lesson_problem")
public class ExamProblemEntity {
	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * 课堂ID
	 */
	private Long examId;

	/**
	 * 问题ID
	 */
	private Long problemId;

	/**
	 * 问题名称（冗余，提高效率）
	 */
	private String problemName;

	/**
	 * 问题类型(选择填空代码)
	 */
	private Integer problemType;

	/**
	 * 分数
	 */
	private Integer score;

	/**
	 * 顺序
	 */
	private Integer sort;


	/**
	 * 来源
	 */
	private Integer source;


	/**
	 * 更新时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;

}