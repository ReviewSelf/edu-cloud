package net.edu.module.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import net.edu.framework.mybatis.entity.BaseEntity;

/**
 * 考核点权重
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-29
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("archive_weight_target_assess")
public class ArchiveWeightTargetAssessEntity extends BaseEntity {
	/**
	* 考核点编号
	*/
	private Long assessId;

	/**
	* 指标点编号
	*/
	private Long targetId;

	/**
	* 课程编号
	*/
	private Long courseId;

	/**
	* 支撑度（百分比）
	*/
	private Double weight;

	/**
	* 支撑说明
	*/
	private String weightExplain;

}