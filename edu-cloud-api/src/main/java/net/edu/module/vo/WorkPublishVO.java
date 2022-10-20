package net.edu.module.vo;

import lombok.Data;

/**
 * @author weng
 * @date 2022/10/10 - 15:15
 **/
@Data
public class WorkPublishVO {

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 作业科目
     */
    private String subject;

    /**
     * 作业内容
     */
    private String task;

    /**
     * 发送时间
     */
    private String sendTime;

    /**
     * 发送用户的id
     */
    private Long userId;


    public String toJsonString() {
        return "{\"studentName\":\""+studentName
                +"\",\"subject\":\""+subject
                +"\",\"task\":\""+task
                +"\"}";
    }
}