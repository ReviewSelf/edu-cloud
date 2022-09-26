package net.edu.module.service.impl;

import net.edu.module.dao.MessageDao;
import net.edu.module.entity.UserEntity;
import net.edu.module.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author weng
 * @date 2022/9/26 - 15:35
 **/
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao messageDao;
    @Override
    public void insertOpenId(String openId) {
        messageDao.insertId(openId);
    }

    @Override
    public void post(UserEntity userEntity){
        messageDao.save(userEntity);
    }
}