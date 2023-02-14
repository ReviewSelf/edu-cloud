package net.edu.module.dao;

import net.edu.module.vo.UserVO;
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
     * @return
     */
    int updateOpenIdByUsername(String username,String openId,String unionId);

    UserVO selectUserInfoByOpenId(String openId);

    String selectOpenIdByUsername(String username);
}
