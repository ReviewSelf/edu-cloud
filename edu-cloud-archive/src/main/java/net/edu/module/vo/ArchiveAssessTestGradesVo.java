package net.edu.module.vo;

import lombok.Data;

import java.util.List;

@Data
public class ArchiveAssessTestGradesVo {

    //学号
    private String studentId;

    //姓名
    private String studentName;

    //期末考核点所获分数
    private List<ArchiveAssessTestScoreVo> finalScoreList;

    private List<ArchiveAssessTestScoreVo> peaceScoreList;

    private String peaceScore;

    private Double weight;

    private Integer id;

    private Integer assessId;

    private Integer score;

    private String stuId;

    private String courseId;

    private String stuName;

    private String summaryId;

    private Double first;
    private Double second;
    private Double third;
    private Double fourth;
    private Double fifth;
    private Double peace;
}
