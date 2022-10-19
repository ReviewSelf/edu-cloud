package net.edu.module.vo;

import lombok.Data;

/**
 * @author weng
 * @date 2022/10/9 - 16:15
 **/
@Data
public class ClassOpenVO  {

    /**
     * 课程名称
     */
    private String className;

    /**
     * 开课时间
     */
    private String classTime;

    /**
     * 开课地点
     */
    private String location;

    /**
     * 发送时间
     */
    private String sendTime;

    /**
     * 发送用户的id
     */
    private Long userId;


    public String toJsonString() {
        return "{\"className\":\""+className
                +"\",\"classTime\":\""+classTime
                +"\",\"location\":\""+location
                +"\"}";
    }
}
