package net.edu.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.util.Date;

/**
 * @author weng
 * @date 2022/10/13 - 14:58
 **/
@Data
public class UserVO {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户id")
    private Integer id;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "真实姓名")
    private String realName;

    @Schema(description = "手机号码")
    private String phone;

    @Schema(description = "unionId")
    private String unionId;

    @Schema(description = "openId(用户对微信公众号唯一标识)")
    private String openId;

    @Schema(description = "所在区域")
    private String area;

    @Schema(description = "详细地址")
    private String address;

    @Schema(description = "机构名")
    private String orgName;

    @Schema(description = "机构id")
    private String orgId;

    @Schema(description = "状态")
    private String status;
}