package net.edu.module.vo;

import lombok.Data;

@Data
public class ExamRecordQuery {
    String name;
    Long examId;
    //0:未参与，1:参与，2:交卷
    Integer status;
    Integer isCorrect;
}
