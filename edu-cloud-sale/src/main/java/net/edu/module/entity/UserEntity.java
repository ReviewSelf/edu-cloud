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
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号码
     */
    private String mobile;

    private String password;
    private String avatar;
    private String realName;
    private Integer gender;
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

    /**
     * 销售id
     */
    private Long saleId;

    /**
     * 新老学员
     */
    private int cadets;

    private Date communicateTime;//上次沟通时间

    private Integer communicate;//沟通次数

    private Integer purposeLevel;//意向等级

    private Integer communication;//沟通方式

    private String explanation;//沟通说明

    private String purpose;//意向说明


}
