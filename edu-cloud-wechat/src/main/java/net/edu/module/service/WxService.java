package net.edu.module.service;

import net.edu.module.entity.AccessToken;
import net.edu.module.vo.UserVO;
import org.springframework.stereotype.Service;

/**
 * @author weng
 * @date 2022/9/24 - 13:56
 **/
@Service
public interface WxService {

    void getAccessToken();
    String createMenu();
    String getUnionId(String openId);
    String getOpenId(String code);


}
