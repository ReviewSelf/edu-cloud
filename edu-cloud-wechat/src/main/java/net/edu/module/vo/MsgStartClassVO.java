package net.edu.module.vo;

import lombok.Data;

@Data
public class MsgStartClassVO {
    private Long id;

    private String className;

    private String beginDate;

    private String endDate;

    private String templateId;
}
