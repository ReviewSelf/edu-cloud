package net.edu.module.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;

/**
 * 流水账管理
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-09-16
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("teach_pay")
public class TeachPayEntity {
	/**
	* 缴费记录id
	*/
	@TableId
	private Integer id;

	/**
	* 缴费金额
	*/
	private String payment;

	/**
	* 实际金额
	*/
	private String paymentTrue;

	/**
	* 用户id
	*/
	private Integer userId;

	/**
	* 缴费时间
	*/
	private Date time;

	/**
	* 操作类型
	*/
	private Integer type;

	/**
	* 备注
	*/
	private String bz;

}