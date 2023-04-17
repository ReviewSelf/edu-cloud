package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.mybatis.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * 课时流水管理
 *
 * @author sqw 
 * @since  2023-03-06
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("teach_class_hours_flow_record")
public class TeachClassHoursFlowRecordEntity extends BaseEntity {
	/**
	* 用户id
	*/
	private Long userId;

	/**
	* 课堂id
	*/
	private Long lessonId;

	/**
	* 说明
	*/
	private String direction;

	/**
	* 常规课时
	*/
	private BigDecimal normal;

	/**
	* 常规赠送课时
	*/
	private BigDecimal normalPresent;

	/**
	* 集训课时
	*/
	private BigDecimal training;

	/**
	* 集训赠送课时
	*/
	private BigDecimal trainingPresent;

	/**
	* 类型：0是退课时、1是加课时、2是扣课时
	*/
	private Integer type;

}