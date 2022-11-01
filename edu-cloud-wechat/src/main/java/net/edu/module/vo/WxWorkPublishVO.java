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
    private String keyword1;

    /**
     * 作业内容
     */
    private String keyword2;

    /**
     * 作业内容
     */
    private String keyword3;

    /**
     * 发送时间
     */
    private String sendTime;

    /**
     * 发送用户的id
     */
    private Long userId;


    public String toJsonString() {
        return "{\"keyword1\":\""+keyword1
                +"\",\"keyword2\":\""+keyword2
                +"\",\"keyword3\":\""+keyword3
                +"\"}";
    }
}
