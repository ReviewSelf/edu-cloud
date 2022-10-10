package net.edu.module.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import net.edu.framework.mybatis.entity.BaseEntity;

import java.util.Date;

/**
 * 教学评价
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-08
 */

@Data
@TableName("teach_evaluate")
public class TeachEvaluateEntity  {
	/**
	* 评价数据id
	*/
	@TableId
	private Long id;

	/**
	* 评价人id
	*/
	private Integer evaluateUserId;



	/**
	* 被评价人id
	*/
	private Integer evaluatedUserId;



	/**
	* 评价得分
	*/
	private Integer score;

	/**
	* 班级id
	*/
	private Integer classId;


	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	* 类型1：老师评价学生，2：学生评价老师
	*/
	private Integer type;

	/**
	* 评价内容
	*/
	private String content;

	/**
	 * 删除标记
	 */
	@TableLogic
	@TableField(fill = FieldFill.INSERT)
	private Integer deleted;

}