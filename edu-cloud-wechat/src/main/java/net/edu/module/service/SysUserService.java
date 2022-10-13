package net.edu.module.service;

import org.springframework.stereotype.Service;

/**
 * @author weng
 * @date 2022/10/12 - 15:50
 **/
@Service
public interface SysUserService {

    int updateOpenIdByUsername(String username,String password,String openId);
}
