package net.edu.module.vo.lesson;

import lombok.Data;

import java.util.List;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/22 10:32
 * @Version: 1.0
 * @Description:
 */
@Data
public class LessonJudgeRecordVo {
    private Long userId;
    private String username;
    private String name;
    private Long lessonId;
    private List<LessonProblemRecord> problemRecords;

}
