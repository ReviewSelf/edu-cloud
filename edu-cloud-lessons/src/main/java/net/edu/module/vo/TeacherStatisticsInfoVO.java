package net.edu.module.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherStatisticsInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;
    //进行中的作业数
    private Integer homeworkNum;
    //即将截止的作业课堂名称
    private String homeworkName;
    //最近一次作业截止时间
    private String homeworkEndTime;
    //监考的考试数量
    private Integer paperNum;
    //最近一次考试监考地点
    private String paperName;
    //最近一次考试开始时间
    private String papersBeginTime;
    //本周课程量
    private Integer lessonNum;
    //最近一次课程名称
    private String LessonName;
    //最近一次上课时间
    private String lessonBeginTime;

}
