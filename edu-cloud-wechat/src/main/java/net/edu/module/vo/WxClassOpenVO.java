package net.edu.module.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author weng
 * @date 2022/10/9 - 16:15
 **/
@Data
public class WxClassOpenVO extends TemplateBaseVo{

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

    public String toJsonString() {
        return "{\"className\":\""+className
                +"\",\"classTime\":\""+classTime
                +"\",\"location\":\""+location
                +"\"}";
    }
}
