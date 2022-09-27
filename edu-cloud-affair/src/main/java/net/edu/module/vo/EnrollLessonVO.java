package net.edu.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

@Data
@Schema(description = "试听管理")
public class EnrollLessonVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "课程ID")
    private Integer id;

    @Schema(description = "课程名称")
    private String name;

    @Schema(description = "任课老师")
    private String username;

    @Schema(description = "任课老师ID")
    private Integer teacherId;

    @Schema(description = "上课地点")
    private String place;

    @Schema(description = "开课时间")
    private String beginTime;

    @Schema(description = "结课时间")
    private String endTime;
}