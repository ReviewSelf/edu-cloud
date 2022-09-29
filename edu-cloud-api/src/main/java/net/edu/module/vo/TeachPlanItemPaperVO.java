package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "日历试卷联系表")
public class TeachPlanItemPaperVO {

    /**
     * 日历id
     */
    private Long itemId;

    /**
     * 试卷id
     */
    private Long paperId;

    /**
     * 试卷名称
     */
    private String paperName;

    /**
     * 试卷类型 0:预习资料，1:课堂练习，3:课后作业
     */
    private Integer paperType;
}
