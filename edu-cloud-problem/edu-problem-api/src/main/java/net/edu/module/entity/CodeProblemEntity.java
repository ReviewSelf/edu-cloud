package net.edu.module.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;

/**
 * 代码题库表
 *
 * @author 马佳浩 
 * @since 1.0.0 2022-09-05
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("problem_code")
public class CodeProblemEntity {
	@TableId
	private Long id;

	/**
	* 名称
	*/
	private String name;

	/**
	* 说明
	*/
	private String description;

	/**
	* 图片
	*/
	private String img;

	/**
	* 来源
	*/
	private String source;

	/**
	* 提示
	*/
	private String tips;

	/**
	* 建议用时(分)
	*/
	private Integer adviceTime;

	/**
	* 难度
	*/
	private Integer difficulty;

	/**
	* 输入说明
	*/
	private String inputExplain;

	/**
	* 输出说明
	*/
	private String outputExplain;

	/**
	* 样例输入
	*/
	private String sampleInput;

	/**
	* 样例输出
	*/
	private String sampleOutput;

	/**
	* 典型问题
	*/
	private Integer isTypical;

	/**
	* 测试样例数
	*/
	private Integer sampleNum;

	/**
	* 知识点id
	*/
	private Integer kpId;

	/**
	* 提交次数
	*/
	private Integer submitTimes;

	/**
	* 正确次数
	*/
	private Integer correctTimes;

	/**
	* 引用次数
	*/
	private Integer usedNum;

	/**
	* 创建时间
	*/
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;

	/**
	* 更新时间
	*/
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

	/**
	* 最后一次操作人
	*/
	private Long operator;

	/**
	* 是否删除
	*/
	private Integer isDeleted;

	/**
	* 状态
	*/
	private Integer state;

	/**
	* 空间限制(KB)
	*/
	private Integer memoryLimit;

	/**
	* 时间限制
	*/
	private Integer timeLimit;

}