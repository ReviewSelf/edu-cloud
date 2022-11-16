package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * {@code @Description:} TODO
 * {@code @author:} sl
 * {@code @date:} 2022年09月16日 19:38
 */
@Data
@Schema(description = "所有老师")
public class AllTeacherVO {
    @Schema(description = "id")
    private Long id;

    @Schema(description = "姓名")
    private String realName;
}
