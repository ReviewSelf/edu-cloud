package net.edu.module.query;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "考试用户导出excel查询信息")
public class ExamUserExcelQuery {
    private Long examId;

    private List<Long> userIdList;
}
