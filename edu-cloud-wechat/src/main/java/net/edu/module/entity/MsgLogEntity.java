package net.edu.module.entity;

import lombok.Data;

import java.util.Date;

@Data
public class MsgLogEntity {

    private Long id;

    private String content;

    private String createTime;

    private String sendTime;

    private String source;

    private Long userId;

    private int userType;

    private int isPush;

    private int isReceive;

    private String templateId;

    private int type;

    private String sentTime;
}
