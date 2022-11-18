package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "学生表")
public class StudentsVO implements Serializable {

    @Schema(description = "班级ID")
    private Integer classId;

    @Schema(description = "页数")
    private Integer page;

    @Schema(description = "每页大小")
    private Integer size;
}
