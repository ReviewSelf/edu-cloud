package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.mybatis.entity.BaseEntity;

import java.util.Date;

/**
 * XinXiHeShi
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-09-05
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("enroll_user")
public class UserEntity extends BaseEntity {

	/**
	* 微信昵称
	*/
	private String wxName;

	/**
	* 真实姓名
	*/
	private String name;

	/**
	* 手机号码
	*/
	private String phone;

	/**
	* unionId
	*/
	private String unionId;

	/**
	* openId(用户对微信公众号唯一标识)
	*/
	private String openId;

	/**
	* 所在区域
	*/
	private String area;

	/**
	* 详细地址
	*/
	private String address;

	/**
	* 年龄
	*/
	private Integer age;

	/**
	* 年级
	*/
	private Integer grade;

	/**
	* 意向说明
	*/
	private String purpose;

	/**
	* 0为未报名状态，1为报名信息状态，2为未试听状态，3位试听完成状态，4为已缴费状态
	*/
	private Integer status;


	/**
	* 登录时间
	*/
	private Date loginTime;

	/**
	* 开始课次
	*/
	private Integer startNumber;

	/**
	* 结束课次
	*/
	private Integer endNumber;

	/**
	* 备注
	*/
	private String remark;

	/**
	* 积分
	*/
	private String integral;

	/**
	* 余额
	*/
	private String balance;

}
