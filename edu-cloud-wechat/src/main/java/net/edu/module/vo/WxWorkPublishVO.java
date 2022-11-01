package net.edu.module.vo;

import lombok.Data;

/**
 * @author weng
 * @date 2022/10/10 - 15:15
 **/
@Data
public class WxWorkPublishVO {

    /**
     * 截止时间
     */
    private String endTime;

    /**
     * 作业内容
     */
    private String content;

    /**
     * 作业内容
     */
    private String demand;

    /**
     * 发送时间
     */
    private String sendTime;

    /**
     * 发送用户的id
     */
    private Long userId;


    public String toJsonString() {
        return "{\"endTime\":\""+endTime
                +"\",\"content\":\""+content
                +"\",\"demand\":\""+demand
                +"\"}";
    }
}
