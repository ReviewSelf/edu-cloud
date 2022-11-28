package net.edu.module.vo;

import lombok.Data;

import java.util.List;

@Data
public class ArchiveAssessTestGradesVo {

    //学号
    private List<String> studentId;

    //姓名
    private List<String> name;

    //考核点名称
    private List<String> assessName;

    //考核点所获分数
    private String assessScore[][];

    private Double weight;

    private Integer id;

    private Integer assessId;

    private Integer score;

    private String stuId;

    private String courseId;

    private String stuName;

    private String summaryId;

}
