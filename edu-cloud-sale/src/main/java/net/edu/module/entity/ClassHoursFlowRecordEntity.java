package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.mybatis.entity.BaseEntity;

/**
 * 课时流水表
 *
 * @author sqw 
 * @since 1.0.0 2023-02-15
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sale_flow_record")
public class ClassHoursFlowRecordEntity extends BaseEntity {

	/**
	* 班级id
	*/
	private Long classId;

	/**
	 * 用户id
	 */
	private Long userId;

	/**
	* 课时数
	*/
	private Integer classTimes;

	/**
	* 备注
	*/
	private String remarks;

	/**
	* 场景：0是退班、1是插班、2是充值、3是退费
	*/
	private Integer scene;

	/**
	* 状态：0是退费、1是充值、2是扣费
	*/
	private Integer status;





}