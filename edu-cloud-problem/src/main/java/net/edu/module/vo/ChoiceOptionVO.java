package net.edu.module.vo;

import lombok.Data;

@Data
public class ChoiceOptionVO {

    private Long id;

    private Long problemId;

    private String problemOption;

    private Boolean isTrue;

}
