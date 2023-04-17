package net.edu.module.vo;

import lombok.Data;

/**
 * @author weng
 * @date 2022/10/10 - 15:30
 **/
@Data
public class WxSignSuccessVO extends TemplateBaseVo{

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 上课班级
     */
    private String lessonName;

    /**
     * 课程内容
     */
    private String lessonContent;

    /**
     * 上课时间
     */
    private String lessonTime;

    /**
     * 上课地点
     */
    private String lessonLocation;




    public String toJsonString() {
        return "{\"studentName\":\""+studentName
                +"\",\"lessonName\":\""+lessonName
                +"\",\"lessonContent\":\""+lessonContent
                +"\",\"lessonTime\":\""+lessonTime
                +"\",\"lessonLocation\":\""+lessonLocation
                +"\"}";
    }
}
