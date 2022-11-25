package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: TODO
 * @author: sl
 * @date: 2022年11月23日 18:30
 */
@Data
@Schema(description = "课程信息")
public class ArchiveScoreBookClassInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @Schema(description = "课程编号")
    private Integer courseId;

    @Schema(description = "教研系")
    private String majorName;

    @Schema(description = "授课班级名称")
    private String className;

    @Schema(description = "教师")
    private String teacherName;
}
