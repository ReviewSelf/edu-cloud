package net.edu.module.untils;

import cn.hutool.json.JSONObject;
import net.edu.module.entity.Button;
import net.edu.module.entity.CommonButton;
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

        CommonButton mainBtn2 = new CommonButton();
        mainBtn2.setName("课程报名");
        mainBtn2.setType("view");
        mainBtn2.setUrl(enrollmentUrl);

        CommonButton mainBtn1 = new CommonButton();
        mainBtn1.setName("我的账号");
        mainBtn1.setType("view");
        mainBtn1.setUrl(accountBindUrl);
        Menu menu = new Menu();
        menu.setButton(new Button[] {mainBtn2,mainBtn1});

        return new JSONObject(menu).toString();
    }
}
