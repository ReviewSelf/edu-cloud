package net.edu.module.query;

import lombok.Data;

/**
 * @Author: 马佳浩
 * @Date: 2022/10/18 14:12
 * @Version: 1.0
 * @Description:
 */
@Data
public class ExamRecordQuery {
    String name;
    Long examId;
    //0:未参与，1:参与，2:交卷
    Integer status;
    Integer isCorrect;
    Integer page;
    Integer limit;
}
