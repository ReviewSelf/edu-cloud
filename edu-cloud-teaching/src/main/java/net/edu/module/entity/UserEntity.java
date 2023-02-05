package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.mybatis.entity.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
public class UserEntity extends BaseEntity {
    /**
     * 用户名
     */
    private String username;
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
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 机构ID
     */
    private Long orgId;
    /**
     * 超级管理员   0：否   1：是
     */
    private Integer superAdmin;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 机构名称
     */
    @TableField(exist=false)
    private String orgName;

    private String unionId;

    private String province;

    private String city;

    private String district;
    private String area;

    private String address;

    private Integer age;

    private Integer grade;

    private String integral;

    private Integer balance;

    private Integer totalAmount;

    private String openId;

    private Integer urgentPhone;

    private String readingSchool;

    private Integer likesNum;

    @TableField(exist=false)
    private Integer readingClassNum;

    @TableField(exist=false)
    private Integer payingClassNum;



}
