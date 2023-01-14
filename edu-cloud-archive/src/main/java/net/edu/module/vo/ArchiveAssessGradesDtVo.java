package net.edu.module.vo;

import lombok.Data;

import java.util.List;

@Data
public class ArchiveAssessGradesDtVo {
    private String name;
    private List<Integer> appraise;
    private String assess1;
    private String assess2;
    private String assess3;
    private String assess4;
    private String assess5;
}
