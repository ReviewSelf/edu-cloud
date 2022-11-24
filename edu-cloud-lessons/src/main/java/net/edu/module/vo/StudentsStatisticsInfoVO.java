package net.edu.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentsStatisticsInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;
    //进行中的作业数量
    private Integer homeworkNum;
    //近一次作业题目总数
    private Integer homeworkProblemNum;
    //近一次作业完成题目数
    private Integer homeworkCompleteProblemNum;
    //最近一次作业截止时间
    private String homeworkEndTime;
    //考试数量
    private String paperName;
    //考试未交卷数量
    private Integer unpaidPapersNum;
    //最近一次考试截止时间
    private String papersEndTime;
    //本周课程量
    private Integer lessonNum;
    //总剩余课程量
    private Integer UnpaidLessonNum;
    //最近一次上课时间
    private String lessonBeginTime;
    //未完成课堂练习总量
    private Integer UnpaidClassProblemNum;

}
