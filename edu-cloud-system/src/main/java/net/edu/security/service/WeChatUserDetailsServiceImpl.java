package net.edu.security.service;

import lombok.AllArgsConstructor;
import net.edu.framework.security.wechat.WeChatUserDetailsService;
import net.edu.system.dao.SysUserDao;
import net.edu.system.entity.SysUserEntity;
import net.edu.system.service.SysUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author: 马佳浩
 * @Date: 2022/10/11 12:03
 * @Version: 1.0
 * @Description:
 */
@Service
@AllArgsConstructor
public class WeChatUserDetailsServiceImpl implements WeChatUserDetailsService {

    private final SysUserDetailsService sysUserDetailsService;

    private final SysUserDao sysUserDao;
    @Override
    public UserDetails loadUserByUnionId(String unionId) throws UsernameNotFoundException {
        SysUserEntity userEntity = sysUserDao.getByUnionId(unionId);
        if (userEntity == null) {
            throw new UsernameNotFoundException("微信用户不存在");
        }

        return sysUserDetailsService.getUserDetails(userEntity);
    }
}
