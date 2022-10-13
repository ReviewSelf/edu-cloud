package net.edu.module.service.impl;

import net.edu.module.dao.SysUserDao;
import net.edu.module.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author weng
 * @date 2022/10/12 - 15:51
 **/
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserDao sysUserDao;
    @Override
    public int updateOpenIdByUsername(String username, String password,String openId) {
        return sysUserDao.updateOpenIdByUsername(username,password,openId);
    }
}