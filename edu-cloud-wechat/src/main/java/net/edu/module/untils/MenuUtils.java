package net.edu.module.untils;

import cn.hutool.json.JSONObject;
import net.edu.module.entity.Button;
import net.edu.module.entity.CommonButton;
import net.edu.module.entity.ComplexButton;
import net.edu.module.entity.Menu;

/**
 * @Author: 马佳浩
 * @Date: 2022/10/13 18:24
 * @Version: 1.0
 * @Description:
 */
public class MenuUtils {


    public static String setMenuBody(){
        String enrollmentUrl = WeChatApiUtils.getAuthBaseUrl(WeChatProperties.CLASS_URL,WeChatApiUtils.SNSAPI_USERINFO);

        String accountBindUrl = WeChatApiUtils.getAuthBaseUrl(WeChatProperties.ACCOUNT_URL,WeChatApiUtils.SNSAPI_USERINFO);

        String feedbackUrl = WeChatApiUtils.getAuthBaseUrl(WeChatProperties.FEEDBACK_URL,WeChatApiUtils.SNSAPI_USERINFO);


        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("我要学习");

        CommonButton sub_Btn21 = new CommonButton();
        sub_Btn21.setName("课程报名");
        sub_Btn21.setType("view");
        System.out.println(enrollmentUrl);
        sub_Btn21.setUrl(enrollmentUrl);

        CommonButton sub_Btn22 = new CommonButton();
        sub_Btn22.setName("试听反馈");
        sub_Btn22.setType("view");
        sub_Btn22.setUrl(feedbackUrl);

        CommonButton sub_Btn23 = new CommonButton();
        sub_Btn23.setName("小程序答题");
        sub_Btn23.setType("click");
        sub_Btn23.setKey("23");

        mainBtn2.setSub_button(new Button[] {sub_Btn23,sub_Btn22,sub_Btn21});

        CommonButton mainBtn1 = new CommonButton();
        mainBtn1.setName("我的账号");
        mainBtn1.setType("view");
        mainBtn1.setUrl(accountBindUrl);
        Menu menu = new Menu();
        menu.setButton(new Button[] {mainBtn2,mainBtn1});

        return new JSONObject(menu).toString();
    }
}
