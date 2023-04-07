package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "生成课堂评价的参数")
public class EvaluateGenerateQuery{
    @Schema(description = "课堂Id")
    private Long lessonId;
    @Schema(description = "优秀指数")
    private Integer excellent;
    @Schema(description = "良好指数")
    private Integer medium;
    @Schema(description = "不合格指数")
    private Integer fail;
}
