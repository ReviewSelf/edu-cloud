package net.edu.module.vo.exam;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 导出考试成绩
 *
 * @author 小樊 babamu@126.com
 * @since 1.0.0 2022-11-01
 */
@Data
@Schema(description = "导出考试成绩")
public class ExamUserExcelVo {
    private Long userId;

    private String name;

    private Long examId;

    private List<ExamExcelVo> problemInfoList;

}
