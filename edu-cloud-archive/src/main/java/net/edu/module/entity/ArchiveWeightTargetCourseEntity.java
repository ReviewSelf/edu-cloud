package net.edu.module.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import net.edu.framework.mybatis.entity.BaseEntity;


/**
 * 一级知识点权重
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-29
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("archive_weight_target_course")
public class ArchiveWeightTargetCourseEntity extends BaseEntity {
	/**
	* 指标点编号
	*/
	private Long targetId;

	/**
	* 课程编号
	*/
	private Long courseId;

	/**
	* 权重
	*/
	private Double weight;

	/**
	 * 教学目标
	 */
	private String teachTarget;

	/**
	 * 达成途径
	 */
	private String approach;

	/**
	 * 评价依据
	 */
	private String evaluationBasis;

	/**
	 * 评价方式
	 */
	private String evaluationMethod;

}
