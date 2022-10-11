package net.edu.module.vo;

import lombok.Data;

import java.util.List;
@Data
public class LessonJudgeRecordVo {
    private Long userId;
    private String name;
    private Long lessonId;
    private List<LessonProblemRecord> problemRecords;
}
