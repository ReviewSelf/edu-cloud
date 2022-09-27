package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 班级用户表
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-09-16
 */
@Data
@Schema(description = "班级学生表")
public class TeachClassStudentVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "自增主键")
    private Long id;

    @Schema(description = "班级id")
    private Long classId;

    @Schema(description = "学生id")
    private Long userId;

    @Schema(description = "学生姓名")
    private String username;

}