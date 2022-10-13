package net.edu.module.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author weng
 * @date 2022/10/12 - 15:43
 **/
@Mapper
public interface SysUserDao {
    /**
     * 账号绑定
     * @param username
     * @param password
     * @return
     */
    int updateOpenIdByUsername(String username,String password,String openId);
}
