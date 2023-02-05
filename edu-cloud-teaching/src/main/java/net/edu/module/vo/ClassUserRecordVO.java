package net.edu.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.util.Date;

@Data
public class ClassUserRecordVO {

    @Schema(description = "编号")
    private Long id;

    @Schema(description = "类型")
    private Integer type;

    @Schema(description = "时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date time;

    @Schema(description = "学生id")
    private Long studentId;

    @Schema(description = "教学老师id")
    private Long teacherId;

    @Schema(description = "原班级id")
    private Long oldClassId;

    @Schema(description = "原课堂id")
    private Long oldLessonId;

    @Schema(description = "新班级id")
    private Long newClassId;

    @Schema(description = "新课堂id")
    private Long newLessonId;


}
