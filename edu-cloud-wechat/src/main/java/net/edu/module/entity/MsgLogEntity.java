package net.edu.module.entity;

import lombok.Data;

import java.util.Date;

@Data
public class MsgLogEntity {

    private Long id;

    private String content;

    private Date createTime;

    private Date sendTime;

    private String source;

    private Long userId;

    private int userType;

    private int isPush;

    private int isReceive;

    private String templateId;

    private int type;

    private Date sentTime;
}
