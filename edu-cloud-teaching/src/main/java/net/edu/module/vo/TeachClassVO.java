package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;
import net.edu.framework.common.utils.DateUtils;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * 班级表
 *
 * @author wengruichen babamu@126.com
 * @since 1.0.0 2022-09-09
 */
@Data
@Schema(description = "班级表")
public class TeachClassVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "班级ID")
    private Long id;

    @Schema(description = "班级名称")
    private String name;

    @Schema(description = "班级介绍")
    private String description;

    @Schema(description = "班级封面")
    private String img;

    @Schema(description = "引用教学计划")
    private Long planId;

    @Schema(description = "总课时")
    private Integer lessonNum;

    @Schema(description = "上课开始时间")
    private Time lessonBeginTime;

    @Schema(description = "上课结束时间")
    private Time lessonEndTime;

    @Schema(description = "周频,0则表示无")
    private Integer frequency;

    @Schema(description = "班级状态，0=未发布，1=未开班，2=开班中，3=结班")
    private Integer status;

    @Schema(description = "班级预计开始时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date beginTime;

    @Schema(description = "班级预计结束时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date endTime;

    @Schema(description = "班主任")
    private Integer teacher1Id;

    @Schema(description = "任课老师")
    private Integer teacher2Id;

    @Schema(description = "下一次课程id")
    private Integer nextLesson;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date createTime;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date updateTime;

    @Schema(description = "逻辑删除")
    private Integer deleted;

    @Schema(description = "更新人")
    private Long updater;

    @Schema(description = "创建人")
    private Long creator;

    @Schema(description = "版本")
    private Integer version;

    @Schema(description = "学生列表")
    private List userIdList;

    @Schema(description = "班主任名称")
    private String teacher1Name;

    @Schema(description = "任课老师名称")
    private String teacher2Name;

    @Schema(description = "上课地点")
    private String place;
}
