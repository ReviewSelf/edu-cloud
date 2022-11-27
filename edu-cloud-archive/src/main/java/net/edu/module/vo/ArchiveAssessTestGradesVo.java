package net.edu.module.vo;

import lombok.Data;

@Data
public class ArchiveAssessTestGradesVo {

    //学号
    private String studentId[];

    //姓名
    private String name[];

    //考核点名称
    private String assessName[];

    //考核点所获分数
    private Integer grades[][];

}
