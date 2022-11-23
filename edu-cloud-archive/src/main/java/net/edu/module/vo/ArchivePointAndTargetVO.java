package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: TODO
 * @author: sl
 * @date: 2022年11月16日 17:38
 */
@Data
@Schema(description = "pointAndTarget")
public class ArchivePointAndTargetVO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Schema(description = "课程编号")
    private Long id;

    @Schema(description = "教学目标ID")
    private Long teachId;

    @Schema(description = "外部系统编号")
    private Long sysId;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "开设学期")
    private String semester;

    @Schema(description = "任课老师")
    private String teacher;

    @Schema(description = "教学班级")
    private String teachClass;

    @Schema(description = "学分")
    private String credit;

    @Schema(description = "上课地点")
    private String place;

    @Schema(description = "考核方式")
    private String assessment;

    @Schema(description = "上课周期")
    private String classCycle;

    @Schema(description = "课程类别")
    private String courseCategory;

    @Schema(description = "教学目标")
    private String teachTarget;


    @Schema(description = "达成途径")
    private String approach;


    @Schema(description = "评价依据")
    private String evaluationBasis;


    @Schema(description = "评价方式")
    private String evaluationMethod;

    @Schema(description = "教学目标名称")
    private String goalName;

    @Schema(description = "指标点编号")
    private Long pointId;

//    @Schema(description = "指标点名称")
//    private String pointDescription;
//
//    @Schema(description = "指标点描述")
//    private String content;

//    @Schema(description = "毕业要求id")
//    private Long graduateId;
//
//    @Schema(description = "毕业要求标题")
//    private String title;
//
//    @Schema(description = "毕业要求描述")
//    private String graduateContent;
    @Schema(description = "指标点描述")
    private String TargetDescription;

    @Schema(description = "毕业要求")
    private String graduateRequire;

}
