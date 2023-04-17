package net.edu.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.util.Date;
import java.util.List;

@Data
@Schema(description = "插班换班退班信息")
public class TeachClassUserDataVO {

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "退出班级id")
    private Long quiteClassId;

    @Schema(description = "加入班级id")
    private Long joinClassId;

    @Schema(description = "加入时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date joinTime;

    @Schema(description = "退出时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date quitTime;

    @Schema(description = "退还课时")
    private Integer quiteClassHours;

    @Schema(description = "扣除课时")
    private Integer joinClassHours;

    @Schema(description = "类型：1插班，2换班，3退班")
    private Integer type;

    @Schema(description = "备注")
    private String remarks;

    @Schema(description = "加入课程列表")
    private List<Long> joinLessonList;

    @Schema(description = "退出课程列表")
    private List<Long> quiteLessonList;

}
