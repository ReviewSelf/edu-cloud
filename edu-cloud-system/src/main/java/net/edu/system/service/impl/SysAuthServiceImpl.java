package net.edu.system.service.impl;


import lombok.AllArgsConstructor;
import net.edu.framework.common.constant.Constant;
import net.edu.framework.common.exception.ServerException;
import net.edu.framework.security.cache.TokenStoreCache;
import net.edu.framework.security.user.UserDetail;
import net.edu.framework.security.utils.TokenUtils;
import net.edu.framework.security.wechat.WeChatAuthenticationToken;
import net.edu.system.enums.LoginOperationEnum;
import net.edu.system.service.*;
import net.edu.system.vo.SysAccountLoginVO;
import net.edu.system.vo.SysTokenVO;
import net.edu.system.vo.SysWeChatLoginVO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限认证服务
 *
 * @author 阿沐 babamu@126.com
 */
@Service
@AllArgsConstructor
public class SysAuthServiceImpl implements SysAuthService {
    private final SysCaptchaService sysCaptchaService;
    private final TokenStoreCache tokenStoreCache;
    private final AuthenticationManager authenticationManager;
    private final SysLogLoginService sysLogLoginService;

    private final SysUserRoleService sysUserRoleService;
    private final SysUserService sysUserService;
    
    @Override
    public SysTokenVO loginByAccount(SysAccountLoginVO login) {
        // 验证码效验
        boolean flag = sysCaptchaService.validate(login.getKey(), login.getCaptcha());
        if (!flag) {
            // 保存登录日志
            sysLogLoginService.save(login.getUsername(), Constant.FAIL, LoginOperationEnum.CAPTCHA_FAIL.getValue());

            throw new ServerException("验证码错误");
        }

        Authentication authentication;
        try {
            // 用户认证
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        } catch (BadCredentialsException e) {
            throw new ServerException("用户名或密码错误");
        }

        // 用户信息
        UserDetail user = (UserDetail) authentication.getPrincipal();

        // 用户角色列表
        List<Long> roleIdList = sysUserRoleService.getRoleIdList(user.getId());
        user.setRoleIdList(roleIdList);

        // 生成 accessToken
        String accessToken = TokenUtils.generator();

        // 保存用户信息到缓存
        tokenStoreCache.saveUser(accessToken, user);

        return new SysTokenVO(accessToken);
    }

    @Override
    public SysTokenVO loginByUnionId(SysWeChatLoginVO login) {
        System.out.println(login.getUnionId());
        Authentication authentication;
        try {
            // 用户认证
            authentication = authenticationManager.authenticate(
                    new WeChatAuthenticationToken(login.getUnionId()));
        } catch (BadCredentialsException e) {
            throw new ServerException("微信用户不存在");
        }
        // 用户信息
        UserDetail user = (UserDetail) authentication.getPrincipal();
        // 生成 accessToken
        String accessToken = TokenUtils.generator();

        // 保存用户信息到缓存
        tokenStoreCache.saveUser(accessToken, user);

        return new SysTokenVO(accessToken);
    }


    @Override
    public void logout(String accessToken) {
        // 用户信息
        UserDetail user = tokenStoreCache.getUser(accessToken);

        // 删除用户信息
        tokenStoreCache.deleteUser(accessToken);

        // 保存登录日志
        sysLogLoginService.save(user.getUsername(), Constant.SUCCESS, LoginOperationEnum.LOGOUT_SUCCESS.getValue());
    }
}
