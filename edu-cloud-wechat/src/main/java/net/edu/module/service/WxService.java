package net.edu.module.service;

import cn.hutool.json.JSONObject;
import net.edu.module.entity.AccessToken;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.jar.JarEntry;

/**
 * @author weng
 * @date 2022/9/24 - 13:56
 **/
@Service
public interface WxService {

    AccessToken getAccessToken();
    String createMenu();

    void messageTemplate(JSONObject jsonObject);
    void template();
}
