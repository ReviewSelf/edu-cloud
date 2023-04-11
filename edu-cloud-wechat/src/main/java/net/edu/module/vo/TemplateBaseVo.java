package net.edu.module.vo;

import lombok.Data;

/**
 * @author weng
 * @date 2023/4/11 - 14:07
 **/
@Data
public abstract class TemplateBaseVo {
    /**
     * 发送时间
     */
    private String sendTime;

    /**
     * 发送用户的id
     */
    private Long userId;

    public abstract String toJsonString();
}