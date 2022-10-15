package net.edu.module.service.impl;

import net.edu.module.api.EduSysApi;
import net.edu.module.dao.SysUserDao;
import net.edu.module.service.SysUserService;
import net.edu.module.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author weng
 * @date 2022/10/12 - 15:51
 **/
@Service

public class SysUserServiceImpl implements SysUserService {


    @Autowired
    EduSysApi eduSysApi;
    @Autowired
    SysUserDao sysUserDao;
    @Override
    public int updateOpenIdByUsername(String username, String password,String openId) {
        Boolean flag = eduSysApi.checkUserAndPassword(username, password).getData();
        System.out.println(openId);
        //如果账号和密码验证成功
        if(flag){
            //如果该账号没有openId，则进行更新
            String result = sysUserDao.selectOpenIdByUsername(username);
            System.out.println(result);
            if(result==null){
                return sysUserDao.updateOpenIdByUsername(username,openId);
            }
            //否则，提示该账号已绑定
//            前端没有接收到数据
        }
        return 0;
    }

    @Override
    public UserVO getUserInfo(String openId) {
        return sysUserDao.selectUserInfoByOpenId(openId);
    }
}