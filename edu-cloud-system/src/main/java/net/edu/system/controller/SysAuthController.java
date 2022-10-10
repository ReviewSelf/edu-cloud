package net.edu.system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.framework.security.utils.TokenUtils;
import net.edu.system.service.SysAuthService;
import net.edu.system.service.SysCaptchaService;
import net.edu.system.vo.SysAccountLoginVO;
import net.edu.system.vo.SysCaptchaVO;
import net.edu.system.vo.SysMobileLoginVO;
import net.edu.system.vo.SysTokenVO;
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

        System.out.println("token"+token);
        return Result.ok(token);
    }


    @PostMapping("logout")
    @Operation(summary = "退出")
    public Result<String> logout(HttpServletRequest request) {
        sysAuthService.logout(TokenUtils.getAccessToken(request));

        return Result.ok();
    }
}
