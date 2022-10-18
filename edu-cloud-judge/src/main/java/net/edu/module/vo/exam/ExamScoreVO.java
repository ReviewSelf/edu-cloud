package net.edu.module.vo.exam;

import lombok.Data;

import java.util.List;

/**
 * @Author: 马佳浩
 * @Date: 2022/10/18 9:55
 * @Version: 1.0
 * @Description:
 */
@Data
public class ExamScoreVO {
    private Long userId;
    private String name;
    private Long examId;
    private List<ExamProblemRecord> problemRecords;


}
