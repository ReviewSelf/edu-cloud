package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("sys_user")
public class UserEntity extends BaseEntity {
    /**
     * 用户名
     */
    private String username;

    /**
     * 学号
     */
    private String stuNumber;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别   0：男   1：女   2：未知
     */
    private Integer gender;

    private String email;

    private String mobile;

    private Long orgId;

    private Integer status;

    private String unionId;

    private String province;

    private String city;

    private String district;

    private String area;

    private String address;

    private Integer age;

    private Integer grade;

    private String integral;

    private String balance;

    private String totalAmount;

    private String openId;

    private String urgentPhone;

    private String urgentIdentity;

    private String readingSchool;

    private Integer likesNum;

    private String purpose;

    private Integer purposeLevel;

    private Long saleId;

    private Integer saleStatus;

    @TableField(exist=false)
    private Integer readingClassNum;

    @TableField(exist=false)
    private Integer payingClassNum;


}
