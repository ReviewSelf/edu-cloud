package net.edu.module.vo.lesson;

import lombok.Data;

import java.util.Date;

@Data
public class LessonProblemRecord {
    private Long recordId;
    private String spendTime;
    private Integer submitStatus;
    private Long problemId;
    private Integer problemType;
    private String problemName;
    private Integer type;
    private Date submitTime;
    private Date beginTime;
}
