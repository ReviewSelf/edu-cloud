package net.edu.module.vo;

import lombok.Data;

import java.util.List;

@Data
public class ExamScoreVO {
    private Long userId;
    private String name;
    private Long examId;
    private List<ExamProblemRecord> problemRecords;
}
