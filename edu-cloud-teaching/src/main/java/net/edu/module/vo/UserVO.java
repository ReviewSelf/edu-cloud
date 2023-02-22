package net.edu.module.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 用户
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@Schema(description = "用户")
public class UserVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ExcelIgnore
    @Schema(description = "id")
    private Long id;

    @Schema(description = "用户名", required = true)
    @NotBlank(message = "用户名不能为空")
    @ExcelProperty(index = 0)
    private String username;

    @ExcelIgnore
    @Schema(description = "密码")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ExcelProperty(index = 1)
    @Schema(description = "姓名", required = true)
    @NotBlank(message = "姓名不能为空")
    private String realName;

    @ExcelIgnore
    @Schema(description = "头像")
    private String avatar;

    @ExcelProperty(index = 2)
    @Schema(description = "性别 0：男   1：女   2：未知", required = true)
    @Range(min = 0, max = 2, message = "性别不正确")
    private Integer gender;

    @ExcelProperty(index = 3)
    @Schema(description = "邮箱")
    @Email(message = "邮箱格式不正确")
    private String email;

    @ExcelProperty(index = 4)
    @Schema(description = "手机号", required = true)
    @NotBlank(message = "手机号不能为空")
    private String mobile;

    @ExcelProperty(index = 5)
    @Schema(description = "机构ID", required = true)
    @NotNull(message = "机构ID不能为空")
    private Long orgId;

    @ExcelIgnore
    @Schema(description = "状态 0：停用    1：正常", required = true)
    @Range(min = 0, max = 1, message = "用户状态不正确")
    private Integer status;

    @ExcelIgnore
    @Schema(description = "角色ID列表")
    private List<Long> roleIdList;

    @ExcelIgnore
    @Schema(description = "岗位ID列表")
    private List<Long> postIdList;

    @ExcelIgnore
    @Schema(description = "超级管理员   0：否   1：是")
    private Integer superAdmin;

    @ExcelIgnore
    @Schema(description = "机构名称")
    private String orgName;

    @ExcelIgnore
    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date createTime;

    @ExcelIgnore
    private String unionId;

    @ExcelProperty(index = 6)
    private String address;

    @ExcelProperty(index = 7)
    private String province;

    @ExcelProperty(index = 8)
    private String city;

    @ExcelProperty(index = 9)
    private String district;

    @ExcelProperty(index = 10)
    private String area;

    @ExcelProperty(index = 11)
    private Integer age;

    @ExcelProperty(index = 12)
    private Integer grade;

    @ExcelIgnore
    private String integral;

    @ExcelIgnore
    private Integer balance;

    @ExcelIgnore
    private Integer totalAmount;

    @ExcelIgnore
    private BigDecimal totalMoney;

    @ExcelIgnore
    private String openId;

    @ExcelIgnore
    private String urgentPhone;

    @ExcelIgnore
    private String urgentIdentity;

    @ExcelIgnore
    private String readingSchool;

    @ExcelIgnore
    private Integer likesNum;

    @ExcelIgnore
    private Integer purposeLevel;

    @ExcelIgnore
    private Integer readingClassNum;

    @ExcelIgnore
    private Integer payingClassNum;


}
