package net.edu.mini.vo;

import lombok.Data;

@Data
public class MyLessonVo {

    //课程名称
    private String lessonName;

    //讲课老师
    private String lessonTeacher;

    //上课时间
    private String lessonTime;

    private String lessonEnd;
}
