package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.common.query.Query;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author 马佳浩
 * @date 2022/12/9 09:15:30
 * @description
 */
@Data
public class LessonChatLogQuery  {

    private Long lessonId;
    private Long id;

    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码最小值为 1")
    @Schema(description = "当前页码", required = true)
    Integer page;

    @NotNull(message = "每页条数不能为空")
    @Range(min = 1, max = 1000, message = "每页条数，取值范围 1-1000")
    @Schema(description = "每页条数", required = true)
    Integer limit;
}
