package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class EnrollUserEntity extends BaseEntity {


	/**
	* 微信昵称
	*/
	private String wxName;

	/**
	* 真实姓名
	*/
	private String realName;

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
	* 1为报名信息状态，3成为学员
	*/
	private Integer status;


}
