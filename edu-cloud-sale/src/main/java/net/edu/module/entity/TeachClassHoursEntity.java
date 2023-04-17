package net.edu.module.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 课时
 *
 * @author weng babamu@126.com
 * @since 1.0.0 2023-03-02
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("teach_class_hours")
public class TeachClassHoursEntity {
	@TableId
	private Long id;

	/**
	* 用户id
	*/
	private Long userId;

	/**
	* 常规课时
	*/
	private BigDecimal normal;

	/**
	* 集训课时
	*/
	private BigDecimal training;

	/**
	* 常规赠送课时
	*/
	private BigDecimal normalPresent;

	/**
	* 集训赠送课时
	*/
	private BigDecimal trainingPresent;

	/**
	* 充值总额
	*/
	private BigDecimal totalMoney;

	@TableField(fill = FieldFill.INSERT)
	private Date createTime;

	@TableField(fill = FieldFill.INSERT)
	private Long creator;

	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

	/**
	* 更新者
	*/
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Long updater;

	/**
	* 删除标识  0：正常   1：已删除
	*/
	@TableLogic
	@TableField(fill = FieldFill.INSERT)
	private Integer deleted;

	/**
	* 版本号
	*/
	private Integer version;

}