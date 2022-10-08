package net.edu.module.api;

import net.edu.module.fallback.EduProblemFallBack;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "edu-cloud-wechat", fallbackFactory = EduProblemFallBack.class)
public interface EduWxApi {

}
