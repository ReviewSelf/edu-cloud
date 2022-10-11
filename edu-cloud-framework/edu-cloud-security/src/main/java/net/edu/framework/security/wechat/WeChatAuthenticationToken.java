package net.edu.framework.security.wechat;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * @Author: 马佳浩
 * @Date: 2022/10/11 11:46
 * @Version: 1.0
 * @Description: unionId登录 AuthenticationToken
 */
public class WeChatAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final Object unionId;


    public WeChatAuthenticationToken(Object principal) {
        super(null);
        this.unionId = principal;
        setAuthenticated(false);
    }

    public WeChatAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.unionId = principal;
        super.setAuthenticated(true);
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        Assert.isTrue(!isAuthenticated,
                "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        super.setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return this.unionId;
    }

    @Override
    public Object getPrincipal() {
        return this.unionId;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }
}
