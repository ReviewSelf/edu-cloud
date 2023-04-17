package net.edu.module.vo;

import lombok.Data;

/**
 * @author weng
 * @date 2022/10/10 - 15:26
 **/
@Data
public class WxLessonOpenVO extends TemplateBaseVo{

    /**
     * 课程名称
     */
    private String lessonName;

    /**
     * 上课时间
     */
    private String lessonTime;

    /**
     * 上课地点
     */
    private String lessonLocation;


    public String toJsonString() {
        return "{\"lessonName\":\""+lessonName
                +"\",\"lessonTime\":\""+lessonTime
                +"\",\"lessonLocation\":\""+lessonLocation
                +"\"}";
    }
}
