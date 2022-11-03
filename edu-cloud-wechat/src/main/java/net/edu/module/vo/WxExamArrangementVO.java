package net.edu.module.vo;

import lombok.Data;

/**
 * @author weng
 * @date 2022/11/3 - 14:53
 **/
@Data
public class WxExamArrangementVO {

    private String examName;//考试名称

    private String examTime;//考试时间

    private String examPlace;//考试地点

    private String teacher;//监考老师

    private String sendTime;

    private Long userId;

    public String toJsonString() {
        return "{\"examName\":\""+examName
                +"\",\"examTime\":\""+examTime
                +"\",\"examPlace\":\""+examPlace
                +"\",\"teacher\":\""+teacher
                +"\"}";
    }
}