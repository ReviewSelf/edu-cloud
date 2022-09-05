package net.edu.module.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import net.edu.framework.mybatis.entity.BaseEntity;

import java.util.Date;

/**
 * 选择题库表
 *
 * @author 马佳浩 
 * @since 1.0.0 2022-09-05
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("problem_choice")
public class ChoiceProblemEntity extends BaseEntity {

	/**
	* 类型
	*/
	private Integer type;

	/**
	* 名称
	*/
	private String name;

	/**
	* 问题说明
	*/
	private String description;

	/**
	* 来源
	*/
	private String source;

	/**
	* 提示
	*/
	private String tips;

	/**
	* 建议用时
	*/
	private Integer adviceTime;

	/**
	* 图片
	*/
	private String img;

	/**
	* 难度
	*/
	private Integer difficulty;

	/**
	* 知识点
	*/
	private Long kpId;

	/**
	* 回答次数
	*/
	private Integer submitTimes;

	/**
	* 正确次数
	*/
	private Integer correctTimes;

	/**
	* 典型问题
	*/
	private Integer isTypical;

	/**
	 * 选项数
	 */
	private Integer optionNum;

	/**
	* 引用次数
	*/
	private Integer usedNum;


}