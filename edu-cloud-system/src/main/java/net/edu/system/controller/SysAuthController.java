package net.edu.system.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.edu.framework.common.utils.Result;
import net.edu.framework.security.utils.TokenUtils;
import net.edu.system.service.SysAuthService;
import net.edu.system.service.SysCaptchaService;
import net.edu.system.vo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 认证管理
 *
 * @author 阿沐 babamu@126.com
 */
@RestController
@RequestMapping("auth")
@Tag(name = "认证管理")
@AllArgsConstructor
@Slf4j
public class SysAuthController {
    private final SysCaptchaService sysCaptchaService;
    private final SysAuthService sysAuthService;

    @GetMapping("captcha")
    @Operation(summary = "验证码")
    public Result<SysCaptchaVO> captcha() {
        SysCaptchaVO captchaVO = sysCaptchaService.generate();

        return Result.ok(captchaVO);
    }


    @PostMapping("login")
    @Operation(summary = "账号密码登录")
    public Result<SysTokenVO> login(@RequestBody SysAccountLoginVO login) {
        SysTokenVO token = sysAuthService.loginByAccount(login);
        return Result.ok(token);
    }

    @PostMapping("wxMini")
    @Operation(summary = "微信小程序登录")
    public Result<SysTokenVO> wxMini(@RequestBody SysWeChatLoginVO login) {

        log.info(login.toString());
        SysTokenVO token = sysAuthService.loginByUnionId(login);

        log.info(token.toString());
        return Result.ok(token);
    }


    @GetMapping("checkUser")
    public Result<Boolean> checkUserAndPassword(String username,String password) {
        return Result.ok(sysAuthService.checkUserAndPassword(username,password));
    }



    @PostMapping("logout")
    @Operation(summary = "退出")
    public Result<String> logout(HttpServletRequest request) {
        sysAuthService.logout(TokenUtils.getAccessToken(request));

        return Result.ok();
    }
}
