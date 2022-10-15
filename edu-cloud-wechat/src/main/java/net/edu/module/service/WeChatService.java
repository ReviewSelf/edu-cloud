package net.edu.module.service;

import net.edu.module.entity.InMessage;

public interface WeChatService {
    void getAccessToken();

    String createMenu();

    String getUnionId(String openId);

    String getOpenId(String code);

    Object handleMessage(InMessage inMessage);

    Object miniLogin(String code);
}
