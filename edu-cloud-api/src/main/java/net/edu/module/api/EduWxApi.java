package net.edu.module.api;

import net.edu.module.fallback.EduProblemFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "edu-cloud-wechat", fallbackFactory = EduProblemFallBack.class)
public interface EduWxApi {
    /****************************quartz调用******************************************/
    @GetMapping("wx/getAccessToken")
    void getAccessToken();
}
