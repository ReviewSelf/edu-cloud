package net.edu.module.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;

/**
 * 问题卷关联问题表
 *
 * @author 樊磊 
 * @since 1.0.0 2022-09-06
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("problem_paper_item")
public class ProblemPaperItemEntity {
	/**
	* id
	*/
	@TableId
	private Long id;

	/**
	* 顺序
	*/
	private Integer sort;

	/**
	 * 试卷id
	 */
	private Long paperId;

	/**
	* 题目id
	*/
	private Long problemId;

	/**
	* 分数
	*/
	private Integer score;

	/**
	* 题目名称
	*/
	private String problemName;

	/**
	* 题目类型
	*/
	private Integer problemType;

	/**
	* 创建时间
	*/
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;

}