package net.edu.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

@Data
@Schema(description = "问题题解表")
public class ProblemSolvingVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @Schema(description = "问题ID")
    private Long problemId;

    @Schema(description = "问题类型 1选择 2填空 3代码")
    private Integer problemType;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date createTime;

    @Schema(description = "创建人")
    private Integer creator;

    @Schema(description = "创建人账号")
    private String creatorUserName;

    @Schema(description = "创建人名称")
    private String creatorName;

}
