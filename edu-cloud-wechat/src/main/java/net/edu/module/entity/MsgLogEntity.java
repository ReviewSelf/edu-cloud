package net.edu.module.entity;

import lombok.Data;

@Data
public class MsgLogEntity {
    private Long id;

    private String content;

    private Long userId;

    private String endDate;

    private String templateId;
}
