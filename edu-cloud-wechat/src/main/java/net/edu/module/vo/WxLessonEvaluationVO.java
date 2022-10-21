package net.edu.module.vo;

import lombok.Data;

/**
 * @author weng
 * @date 2022/10/19 - 12:01
 **/
@Data
public class WxLessonEvaluationVO {

    private String studentName;//学生姓名

    private String lessonName;//课程名称

    private String content; //评价内容(备注)

    private Integer rankNum; //名次

    private Integer answeredNum; //答题量

    private Integer correctNum;  //正确量

    private Integer undecidedNum; //未判量

    private Integer errorNum; //错题量

    private String Accuracy ;  //正确率

    private Integer unansweredNum; //未答题

    private String sendTime;

    private Integer lessonId;

    private Long userId;

    //名次加正确率为模板中的评价内容
    public String toJsonString() {
        return "{\"lessonName\":\""+lessonName
                +"\",\"rankNum\":\""+rankNum
                +"\",\"evaluationContent\":\""+content
                +"\"}";
    }
}
