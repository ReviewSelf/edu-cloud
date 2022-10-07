package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "试听管理")
public class EnrollLessonVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "课程ID")
    private Integer id;

    @Schema(description = "课程名称")
    private String name;

    @Schema(description = "任课老师")
    private String realName;

    @Schema(description = "日历ID")
    private Integer planItemId;

    @Schema(description = "日历名称")
    private String  planItemName;

    @Schema(description = "任课老师ID")
    private Integer teacherId;

    @Schema(description = "上课地点")
    private String place;

    @Schema(description = "开课时间")
    private String beginTime;

    @Schema(description = "结课时间")
    private String endTime;
}
