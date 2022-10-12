package net.edu.module.untils;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import net.edu.module.dao.TemplateDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SubscriptionMessageUtil {

    /**
     * 作业发布提醒
     */
    public static void sendHomeWorkMsg(String appid,String appSecret,String userOpenid,String tempId,String content,String userName) {

        /**
         *  {{first.DATA}}
         *  学生姓名：{{name.DATA}}
         *  作业科目：{{subject.DATA}}
         *  作业内容：{{content.DATA}}
         *  {{remark.DATA}}
         */

        String OrderMsgTemplateId = tempId;


        JSONObject jsonObject = JSONUtil.parseObj(content);
        String subject = jsonObject.getStr("subject");
        String task = jsonObject.getStr("task");

        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(appid);
        wxStorage.setSecret(appSecret);

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);

        String[] words = content.split(" ");
        // 此处的 key/value 需和模板消息对应
        List<WxMpTemplateData> wxMpTemplateDataList = Arrays.asList(
                new WxMpTemplateData("first", "您好，您的孩子有新的作业已发布，请查收", "#000000"),
                new WxMpTemplateData("name", userName),
                new WxMpTemplateData("subject", subject),
                new WxMpTemplateData("content",task),
                new WxMpTemplateData("remark", "感谢您的查阅，请及时监督孩子完成作业。")
        );

        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(userOpenid)
                .templateId(OrderMsgTemplateId)
                .data(wxMpTemplateDataList)
                .build();

        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
        }
    }

    /**
     * 课程发布提醒

     */
    public static void sendClassOpenMsg(String appid, String appSecret, String userOpenid,String tempId,String content) {
        /**
         * {{first.DATA}}
         * 课程名称：{{keyword1.DATA}}
         * 开课时间：{{keyword2.DATA}}
         * 开课地点：{{keyword3.DATA}}
         * 联系方式：{{keyword4.DATA}}
         * {{remark.DATA}}
         */
        String OrderMsgTemplateId = tempId;

        // 卡片详情跳转页，设置此值，当点击消息时会打开指定的页面
//        String detailUrl = "https://bing.com";

        JSONObject jsonObject = JSONUtil.parseObj(content);
        String className = jsonObject.getStr("className");
        String classTime = jsonObject.getStr("classTime");
        String location = jsonObject.getStr("location");

        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(appid);
        wxStorage.setSecret(appSecret);

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        String phoneNumber = "13355556666";
        // 此处的 key/value 需和模板消息对应
        List<WxMpTemplateData> wxMpTemplateDataList = Arrays.asList(
                new WxMpTemplateData("first", "您好,您报名的课程即将开课，请务必按时上课。", "#000000"),
                new WxMpTemplateData("keyword1", className),
                new WxMpTemplateData("keyword2", classTime),
                new WxMpTemplateData("keyword3",location),
                new WxMpTemplateData("keyword4","李老师"+phoneNumber),
                new WxMpTemplateData("remark", "如需请假，请及时与我们取得联系。")
        );

        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(userOpenid)
                .templateId(OrderMsgTemplateId)
                .data(wxMpTemplateDataList)
                .build();

        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
        }
    }

    /**
     * 作业及时提交提醒
     */
    public static void sendHomeworkSubmitMsg(String appid, String appSecret, String userOpenid,String tempId,String content) {
        /**
         * {{first.DATA}}
         * 截止时间：{{keyword1.DATA}}
         * 提交方式：{{keyword2.DATA}}
         * {{remark.DATA}}
         */
        String OrderMsgTemplateId = tempId;


        JSONObject jsonObject = JSONUtil.parseObj(content);
        String deadline = jsonObject.getStr("deadline");
        String submitMethod = jsonObject.getStr("submitMethod");


        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(appid);
        wxStorage.setSecret(appSecret);

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);

        // 此处的 key/value 需和模板消息对应
        List<WxMpTemplateData> wxMpTemplateDataList = Arrays.asList(
                new WxMpTemplateData("first","您好，本次课程的课后作业需要您及时提交", "#000000"),
                new WxMpTemplateData("keyword1", deadline),
                new WxMpTemplateData("keyword2", submitMethod),
                new WxMpTemplateData("remark", "如有疑问，请及时与课程老师取得联系。")
        );

        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(userOpenid)
                .templateId(OrderMsgTemplateId)
                .data(wxMpTemplateDataList)
                .build();

        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
        }
    }

    /**
     * 上课签到成功提醒
     */
    public static void sendSignSuccessMsg(String appid, String appSecret, String userOpenid,String tempId,String content,String userName) {

        /**
         * {{first.DATA}}
         * 学生姓名：{{keyword1.DATA}}
         * 上课班级：{{keyword2.DATA}}
         * 课程内容：{{keyword3.DATA}}
         * 上课时间：{{keyword4.DATA}}
         * 上课地点：{{keyword5.DATA}}
         * {{remark.DATA}}
         */
        String OrderMsgTemplateId = tempId;



        JSONObject jsonObject = JSONUtil.parseObj(content);
        String lessonName = jsonObject.getStr("lessonName");
        String lessonContent = jsonObject.getStr("lessonContent");
        String lessonTime = jsonObject.getStr("lessonTime");
        String lessonLocation = jsonObject.getStr("lessonLocation");

        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(appid);
        wxStorage.setSecret(appSecret);

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);

        // 此处的 key/value 需和模板消息对应
        List<WxMpTemplateData> wxMpTemplateDataList = Arrays.asList(
                new WxMpTemplateData("first","您好，您的孩子已完成签到", "#000000"),
                new WxMpTemplateData("keyword1", userName),
                new WxMpTemplateData("keyword2", lessonName),
                new WxMpTemplateData("keyword3", lessonContent),
                new WxMpTemplateData("keyword4", lessonTime),
                new WxMpTemplateData("keyword5", lessonLocation),
                new WxMpTemplateData("remark", "请在下课后及时接走孩子。")
        );

        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(userOpenid)
                .templateId(OrderMsgTemplateId)
                .data(wxMpTemplateDataList)
                .build();

        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
        }
    }


    /**
     *上课前提醒
     */
    public static void sendLessonOpenMsg(String appid, String appSecret, String userOpenid,String tempId,String content) {

        /**
         * {{first.DATA}}
         * 课程名称：{{keyword1.DATA}}
         * 上课时间：{{keyword2.DATA}}
         * 上课地点：{{keyword3.DATA}}
         * 联系电话：{{keyword4.DATA}}
         * {{remark.DATA}}
         */
        String OrderMsgTemplateId = tempId;



        JSONObject jsonObject = JSONUtil.parseObj(content);
        String lessonName = jsonObject.getStr("lessonName");
        String lessonTime = jsonObject.getStr("lessonTime");
        String lessonLocation = jsonObject.getStr("lessonLocation");

        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(appid);
        wxStorage.setSecret(appSecret);

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);

        // 此处的 key/value 需和模板消息对应
        List<WxMpTemplateData> wxMpTemplateDataList = Arrays.asList(
                new WxMpTemplateData("first","您好，请准时参加如下课程：", "#000000"),
                new WxMpTemplateData("keyword1", lessonName),
                new WxMpTemplateData("keyword2", lessonTime),
                new WxMpTemplateData("keyword3", lessonLocation),
                new WxMpTemplateData("keyword4", "1335555666"),
                new WxMpTemplateData("remark", "请提前15分钟到，带好笔记本电脑。")
        );

        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(userOpenid)
                .templateId(OrderMsgTemplateId)
                .data(wxMpTemplateDataList)
                .build();

        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
        }
    }
}



