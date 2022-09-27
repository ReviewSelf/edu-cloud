package net.edu.module.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;

/**
 * 问题资源表
 *
 * @author 周建超 
 * @since 1.0.0 2022-09-20
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("problem_resource")
public class ProblemResourceEntity {
	@TableId
	private Long id;

	/**
	* 问题ID
	*/
	private Long problemId;

	/**
	* 问题类型 1选择 2填空 3代码
	*/
	private Integer problemType;

	/**
	* 文件路径
	*/
	private String filePath;

	/**
	* 文件类型
	*/
	private String fileType;

	/**
	* 创建时间
	*/
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;

	/**
	* 删除
	*/
	@TableLogic
	@TableField(fill = FieldFill.INSERT)
	private String deleted;

	private String remark;

}