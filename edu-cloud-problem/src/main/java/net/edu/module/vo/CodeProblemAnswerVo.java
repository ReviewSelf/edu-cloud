package net.edu.module.vo;

import lombok.Data;

import java.util.List;

@Data
public class CodeProblemAnswerVo {
    private String answer;

    private List<CodeSampleVO> codeSampleVOList;
}
