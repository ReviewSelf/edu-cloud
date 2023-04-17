package net.edu.module.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import net.edu.module.service.Observer;
import net.edu.module.untils.SubscriptionMessageUtil;


import java.util.Arrays;
import java.util.List;

/**
 * @author weng
 * @date 2023/4/10 - 15:27
 **/
//观察者
public class ObserverImpl {

    //作业发布提醒
    public static class HomeWorkMsg implements Observer {
        public String userOpenid;
        private final String tempId = "rxOno-8c3Gv_uWEGO1urOnTq2cyQGGiR-054hUfOJOo";
        @Override
        public void sentMessage(String content) {
                JSONObject jsonObject = JSONUtil.parseObj(content);
                String endTime = jsonObject.getStr("endTime");
                String content1 = jsonObject.getStr("content");
                String demand = jsonObject.getStr("demand");

                List<WxMpTemplateData> wxMpTemplateDataList = Arrays.asList(
                        new WxMpTemplateData("first","同学，你有新的作业，请查收", "#000000"),
                        new WxMpTemplateData("keyword1", endTime),
                        new WxMpTemplateData("keyword2", content1),
                        new WxMpTemplateData("keyword3", demand),
                        new WxMpTemplateData("remark", "")
                );

                SubscriptionMessageUtil.sent(userOpenid, tempId, wxMpTemplateDataList);

        }
    }

    //开班提醒
    public static class ClassOpenMsg implements Observer {
        public String userOpenid;
        private final String tempId = "Hx0hZKpfm1_CCDw_9uEnCTh9Ws-NB6GDONM7_1WuG3Q";

        @Override
        public void sentMessage(String content) {
                JSONObject jsonObject = JSONUtil.parseObj(content);
                String className = jsonObject.getStr("className");
                String classTime = jsonObject.getStr("classTime");
                String location = jsonObject.getStr("location");

                String phoneNumber = "13355556666";
                List<WxMpTemplateData> wxMpTemplateDataList = Arrays.asList(
                        new WxMpTemplateData("first", "您好,您报名的课程即将开课", "#000000"),
                        new WxMpTemplateData("keyword1", className),
                        new WxMpTemplateData("keyword2", classTime),
                        new WxMpTemplateData("keyword3", location),
                        new WxMpTemplateData("keyword4", "李老师" + phoneNumber),
                        new WxMpTemplateData("remark", "如有疑问，请及时与我们取得联系。")
                );

                SubscriptionMessageUtil.sent(userOpenid, tempId, wxMpTemplateDataList);

        }
    }

    //开课提醒
    public static class LessonOpenMsg implements Observer {
        public String userOpenid;
        private final String tempId = "xVaI4nj2XXYtZBM5rSJZfNH2qonpexPlLAAnLlTKsNw";

        @Override
        public void sentMessage(String content) {
            JSONObject jsonObject = JSONUtil.parseObj(content);
            String lessonName = jsonObject.getStr("lessonName");
            String lessonTime = jsonObject.getStr("lessonTime");
            String lessonLocation = jsonObject.getStr("lessonLocation");

            List<WxMpTemplateData> wxMpTemplateDataList = Arrays.asList(
                    new WxMpTemplateData("first", "您好，请带您的孩子准时参加如下课程：", "#000000"),
                    new WxMpTemplateData("keyword1", lessonName),
                    new WxMpTemplateData("keyword2", lessonTime),
                    new WxMpTemplateData("keyword3", lessonLocation),
                    new WxMpTemplateData("keyword4", "1335555666"),
                    new WxMpTemplateData("remark", "如需请假，请及时联系教务老师。")
            );

            SubscriptionMessageUtil.sent(userOpenid, tempId, wxMpTemplateDataList);

        }
    }

    //签到成功
    public static class SignSuccessMsg implements Observer {
        public String userOpenid;
        private final String tempId = "jwKnhcbeGq171pU7v-1r14ZnBKSLnsje5H61eAys5ek";

        @Override
        public void sentMessage(String content) {
            JSONObject jsonObject = JSONUtil.parseObj(content);
            String studentName = jsonObject.getStr("studentName");
            String lessonName = jsonObject.getStr("lessonName");
            String lessonContent = jsonObject.getStr("lessonContent");
            String lessonTime = jsonObject.getStr("lessonTime");
            String lessonLocation = jsonObject.getStr("lessonLocation");

            List<WxMpTemplateData> wxMpTemplateDataList = Arrays.asList(
                    new WxMpTemplateData("first", "您好，您的孩子已完成签到", "#000000"),
                    new WxMpTemplateData("keyword1", studentName),
                    new WxMpTemplateData("keyword2", lessonName),
                    new WxMpTemplateData("keyword3", lessonContent),
                    new WxMpTemplateData("keyword4", lessonTime),
                    new WxMpTemplateData("keyword5", lessonLocation),
                    new WxMpTemplateData("remark", "请在下课后及时接走孩子。")
            );

            SubscriptionMessageUtil.sent(userOpenid, tempId, wxMpTemplateDataList);

        }
    }

    //作业截止
    public static class WorkDeadlineMsg implements Observer {
        public String userOpenid;
        private final String tempId = "Pimzo0M6cV3-U-Q2b-GQQpXOq0Yk4nGrfiGiwXyNow8";

        @Override
        public void sentMessage(String content) {
            JSONObject jsonObject = JSONUtil.parseObj(content);
            String deadline = jsonObject.getStr("deadline");
            String submitMethod = jsonObject.getStr("submitMethod");
            String remark = jsonObject.getStr("remark");

            List<WxMpTemplateData> wxMpTemplateDataList = Arrays.asList(
                    new WxMpTemplateData("first", "您好，本次课程的课后作业还有24小时截止，请您及时提交", "#000000"),
                    new WxMpTemplateData("keyword1", deadline),
                    new WxMpTemplateData("keyword2", submitMethod),
                    new WxMpTemplateData("remark", remark)
            );

            SubscriptionMessageUtil.sent(userOpenid, tempId, wxMpTemplateDataList);

        }
    }

    //课堂评价
    public static class LessonEvaluationMsg implements Observer {
        public String userOpenid;
        private final String tempId = "6v40QEgvhUD48mBKdkZM-nTiw0fk276yXw6SZ7_00uI";


        @Override
        public void sentMessage(String content) {
            JSONObject jsonObject = JSONUtil.parseObj(content);
            String studentName = jsonObject.getStr("studentName");
            String lessonName = jsonObject.getStr("lessonName");
            String evaluationContent = jsonObject.getStr("evaluationContent");

            List<WxMpTemplateData> wxMpTemplateDataList = Arrays.asList(
                    new WxMpTemplateData("first", "您好，您的孩子今日表现如下:", "#000000"),
                    new WxMpTemplateData("keyword1", studentName),
                    new WxMpTemplateData("keyword2", lessonName),
                    new WxMpTemplateData("keyword3", evaluationContent),
                    new WxMpTemplateData("remark", "点击详情查看具体情况。")
            );

            SubscriptionMessageUtil.sent(userOpenid, tempId, wxMpTemplateDataList);

        }
    }

    //考试安排
    public static class ExamArrangementMsg implements Observer {
        public String userOpenid;
        private final String tempId = "l0Ra64ka4Wk6qL3q_VP8LkR5ekx_2J7PMbcD547-Duw";

        @Override
        public void sentMessage(String content) {
            JSONObject jsonObject = JSONUtil.parseObj(content);

            String examName = jsonObject.getStr("examName");
            String examTime = jsonObject.getStr("examTime");
            String examPlace = jsonObject.getStr("examPlace");
            String teacher = jsonObject.getStr("teacher");

            List<WxMpTemplateData> wxMpTemplateDataList = Arrays.asList(
                    new WxMpTemplateData("first", "您好，您的孩子明天有考试安排如下：", "#000000"),
                    new WxMpTemplateData("keyword1", examName),
                    new WxMpTemplateData("keyword2", examTime),
                    new WxMpTemplateData("keyword3", examPlace),
                    new WxMpTemplateData("keyword4", teacher),
                    new WxMpTemplateData("remark", "请及时到场考试，做文明考生。")
            );

            SubscriptionMessageUtil.sent(userOpenid, tempId, wxMpTemplateDataList);

        }
    }
}
