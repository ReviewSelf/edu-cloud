package net.edu.module.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author: 孤影照惊鸿1213
 * @Date: 2022/10/13 10:32
 * @Version: 1.0
 * @Description:
 */
@Data
public class ExamJudgeRecordVo {
    private Long userId;
    private String name;
    private Long examId;
    private List<ExamProblemRecord> problemRecords;

}
