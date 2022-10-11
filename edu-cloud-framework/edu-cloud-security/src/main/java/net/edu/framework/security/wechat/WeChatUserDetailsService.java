package net.edu.framework.security.wechat;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface WeChatUserDetailsService {

    /**
     * 通过UNIONID加载用户信息
     *
     * @param unionId UNIONID
     * @return 用户信息
     * @throws UsernameNotFoundException 不存在异常
     */
    UserDetails loadUserByUnionId(String unionId) throws UsernameNotFoundException;
}
