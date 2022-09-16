package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Description: TODO
 * @author: sl
 * @date: 2022年09月16日 19:38
 */
@Data
@Schema(description = "所有老师")
public class AllTeacherVo {
    @Schema(description = "id")
    private Long id;

    @Schema(description = "姓名")
    private String realName;
}
