package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;

/**
 * @Description: TODO
 * @author: sl
 * @date: 2022年10月10日 16:56
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "用户查询")
public class AffairTeacherQuery extends Query {
    @Schema(description = "用户名")
    private String username;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "性别")
    private Integer gender;
}
