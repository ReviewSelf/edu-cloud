package net.edu.module.service;

import net.edu.module.entity.UserEntity;
import org.springframework.stereotype.Service;

/**
 * @author weng
 * @date 2022/9/26 - 15:35
 **/
@Service
public interface MessageService {

    void insertOpenId(String openId);

    void post(UserEntity userEntity);

    void insertClassUser(Integer classId, String openID);
}
