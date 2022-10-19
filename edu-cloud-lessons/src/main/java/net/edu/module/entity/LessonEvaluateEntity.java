package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 课堂评价
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-18
 */

@Data
@TableName("lesson_evaluate")
public class LessonEvaluateEntity {
	@TableId
	private Long id;

	/**
	* 学生id
	*/
	private Integer userId;

	/**
	* 课堂id
	*/
	private Integer lessonId;

	/**
	* 名次
	*/
	private Integer rankNum;

	/**
	* 未答题
	*/
	private Integer unansweredNum;

	/**
	* 答题量
	*/
	private Integer answeredNum;

	/**
	* 未判量
	*/
	private Integer undecidedNum;

	/**
	* 正确量
	*/
	private Integer correctNum;

	/**
	* 错题量
	*/
	private Integer errorNum;

	/**
	* 评价内容
	*/
	private String content;

	/**
	* 创建时间
	*/
	private Date createTime;

	/**
	* 更新时间
	*/
	private Date updateTime;

}