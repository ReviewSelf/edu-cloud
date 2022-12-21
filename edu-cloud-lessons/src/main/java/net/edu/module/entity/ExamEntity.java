package net.edu.module.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;

import net.edu.framework.mybatis.entity.BaseEntity;

/**
 * 考试
 *
 * @author 小樊 babamu@126.com
 * @since 1.0.0 2022-10-09
 */
@EqualsAndHashCode(callSuper=false)
@Data
@TableName("exam")
public class ExamEntity extends BaseEntity {

	/**
	* 试卷ID
	*/
	private Long paperId;

	/**
	* 考试说明
	*/
	private String description;

	/**
	* 开始时间
	*/
	private Date beginTime;

	/**
	* 结束时间
	*/
	private Date endTime;

	/**
	* 总分
	*/
	private Integer score;

	/**
	* 监考老师
	*/
	private Long teacherId;

	/**
	* 知识点ID
	*/
	private String kpCode;


	/**
	* 考试名称
	*/
	private String name;

	/**
	* 考试地点
	*/
	private String place;


	/**
	 * 问题数量
	 */
	private Integer problemNum;
	/**
	 * 考试时长
	 */
	private Integer timeLimit;

	/**
	 * 是否乱序
	 */
	private Integer problemOrder;

	/**
	 * 是否可以返回上一题
	 */
	private Integer problemBack;

}