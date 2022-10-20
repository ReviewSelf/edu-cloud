package net.edu.module.vo;

import lombok.Data;

/**
 * @author weng
 * @date 2022/10/10 - 15:36
 **/
@Data
public class WxWorkDeadlineVO {

    /**
     * 截止时间
     */
    private String deadline;

    /**
     * 提交方式
     */
    private String submitMethod;


    /**
     * 发送时间
     */
    private String sendTime;

    /**
     * 发送用户的id
     */
    private Long userId;


    public String toJsonString() {
        return "{\"deadline\":\""+deadline
                +"\",\"submitMethod\":\""+submitMethod
                +"\"}";
    }
}
