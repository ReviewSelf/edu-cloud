package net.edu.module.api;

import net.edu.framework.common.utils.Result;
import net.edu.module.fallback.EduProblemFallBack;
import net.edu.module.vo.SysWeChatLoginVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author weng
 * @date 2022/10/13 - 16:27
 **/
@FeignClient(value = "edu-cloud-system", fallbackFactory = EduProblemFallBack.class)
public interface EduSysApi {

    @GetMapping("auth/checkUser")
    Result<Boolean> checkUserAndPassword(@RequestParam(value="username") String username,@RequestParam(value="password") String password);

    @PostMapping("auth/wxMini")
    Result<Object> wxMini(@RequestBody SysWeChatLoginVO login);
}
