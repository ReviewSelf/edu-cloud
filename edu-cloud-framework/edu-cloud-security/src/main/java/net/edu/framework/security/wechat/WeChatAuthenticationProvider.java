package net.edu.framework.security.wechat;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

/**
 * @Author: 马佳浩
 * @Date: 2022/10/11 11:52
 * @Version: 1.0
 * @Description:
 */
public class WeChatAuthenticationProvider implements AuthenticationProvider, InitializingBean, MessageSourceAware {
    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
    private final GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();
    private final WeChatUserDetailsService weChatUserDetailsService;

    public WeChatAuthenticationProvider(WeChatUserDetailsService weChatUserDetailsService) {
        this.weChatUserDetailsService = weChatUserDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(WeChatAuthenticationToken.class, authentication,
                () -> messages.getMessage(
                        "WeChatAuthenticationProvider.onlySupports",
                        "Only WeChatAuthenticationProvider is supported"));

        WeChatAuthenticationToken authenticationToken = (WeChatAuthenticationToken) authentication;
        String unionId = authenticationToken.getName();

        try {
            UserDetails userDetails = weChatUserDetailsService.loadUserByUnionId(unionId);

            if (userDetails == null) {
                throw new BadCredentialsException("Bad credentials");
            }
            return createSuccessAuthentication(authentication, userDetails);
        } catch (UsernameNotFoundException ex) {
            throw new BadCredentialsException(this.messages
                    .getMessage("WeChatAuthenticationProvider.badCredentials", "Bad credentials"));
        }
    }


    protected Authentication createSuccessAuthentication(Authentication authentication, UserDetails user) {
        WeChatAuthenticationToken result = new WeChatAuthenticationToken(user,
                authoritiesMapper.mapAuthorities(user.getAuthorities()));
        result.setDetails(authentication.getDetails());
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return WeChatAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(weChatUserDetailsService, "weChatUserDetailsService must not be null");
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }
}
