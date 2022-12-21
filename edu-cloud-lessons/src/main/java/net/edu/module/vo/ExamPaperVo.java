package net.edu.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;
import net.edu.module.entity.ExamProblemEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: 马佳浩
 * @Date: 2022/10/15 12:24
 * @Version: 1.0
 * @Description:
 */
@Data
public class ExamPaperVo implements Serializable {
    private static final long serialVersionUID = 1L;
    List<ExamProblemEntity> paperProblem;
    //答题位置
    Integer problemIndex;

    Integer back;

    ExamAttendLogVO attendLogVO;
}
