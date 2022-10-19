package net.edu.module.service;

import cn.hutool.json.JSONObject;
import net.edu.framework.common.utils.Result;
import net.edu.module.entity.InMessage;

public interface WeChatService {
    void getAccessToken();

    String createMenu();

    String getUnionId(String openId);

    JSONObject getOpenId(String code);

    Object handleMessage(InMessage inMessage);

    Object miniLogin(String code);
}
