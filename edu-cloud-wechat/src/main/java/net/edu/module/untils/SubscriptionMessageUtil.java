package net.edu.module.untils;

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
    public static void sendHomeWorkMsg(String appid,String appSecret,String userOpenid,String tempId,String content) {

/**
 * {{first.DATA}}
 * 学生姓名：{{name.DATA}}
 * 作业科目：{{subject.DATA}}
 * 作业内容：{{content.DATA}}
 * {{remark.DATA}}
 */
        String OrderMsgTemplateId = tempId;

        // 卡片详情跳转页，设置此值，当点击消息时会打开指定的页面
//        String detailUrl = "https://bing.com";


        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(appid);
        wxStorage.setSecret(appSecret);

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);

        String[] words = content.split(" ");
        // 此处的 key/value 需和模板消息对应
        List<WxMpTemplateData> wxMpTemplateDataList = Arrays.asList(
                new WxMpTemplateData("first", "您好，您的孩子有新的作业已发布，请查收", "#000000"),
                new WxMpTemplateData("name", words[0]),
                new WxMpTemplateData("subject", words[1]),
                new WxMpTemplateData("content",words[2]),
                new WxMpTemplateData("remark", "感谢您的查阅，请及时监督孩子完成作业。")
        );

        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(userOpenid)
                .templateId(OrderMsgTemplateId)
                .data(wxMpTemplateDataList)
//                .url(detailUrl)
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

        String[] words = content.split(" ");
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(appid);
        wxStorage.setSecret(appSecret);

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        String phoneNumber = "13355556666";
        // 此处的 key/value 需和模板消息对应
        List<WxMpTemplateData> wxMpTemplateDataList = Arrays.asList(
                new WxMpTemplateData("first", words[0], "#000000"),
                new WxMpTemplateData("keyword1", words[1]),
                new WxMpTemplateData("keyword2", words[2]),
                new WxMpTemplateData("keyword3",words[3]),
                new WxMpTemplateData("keyword4","李老师"+phoneNumber),
                new WxMpTemplateData("remark", "如有疑问，请及时与我们取得联系。")
        );

        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(userOpenid)
                .templateId(OrderMsgTemplateId)
                .data(wxMpTemplateDataList)
//                .url(detailUrl)
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


        String[] words = content.split(" ");
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(appid);
        wxStorage.setSecret(appSecret);

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);

        // 此处的 key/value 需和模板消息对应
        List<WxMpTemplateData> wxMpTemplateDataList = Arrays.asList(
                new WxMpTemplateData("first","您好，本次课程的课后作业需要您及时提交", "#000000"),
                new WxMpTemplateData("keyword1", words[0]),
                new WxMpTemplateData("keyword2", words[1]),
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
    public static void sendSignSuccessMsg(String appid, String appSecret, String userOpenid,String tempId,String content) {

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


        String[] words = content.split(" ");
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(appid);
        wxStorage.setSecret(appSecret);

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);

        // 此处的 key/value 需和模板消息对应
        List<WxMpTemplateData> wxMpTemplateDataList = Arrays.asList(
                new WxMpTemplateData("first","您好，本次课程的课后作业需要您及时提交", "#000000"),
                new WxMpTemplateData("keyword1", words[0]),
                new WxMpTemplateData("keyword2", words[1]),
                new WxMpTemplateData("keyword3", words[1]),
                new WxMpTemplateData("keyword4", words[1]),
                new WxMpTemplateData("keyword5", words[1]),
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
}



