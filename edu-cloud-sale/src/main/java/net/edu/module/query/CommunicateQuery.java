package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;

import java.util.List;

/**
 * 用户查询
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "用户查询")
public class CommunicateQuery extends Query {
    @Schema(description = "用户名")
    private String username;

    @Schema(description = "学号")
    private String stuNumber;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "真实姓名")
    private String realName;

    @Schema(description = "学生id")
    private Long stuId;
}
