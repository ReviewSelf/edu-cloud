package net.edu.module.api;

import net.edu.module.api.fallback.CodeProblemSampleFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/4 1:53
 * @Version: 1.0
 * @Description:
 */
@FeignClient(value = "edu-cloud-problem", fallback = CodeProblemSampleFallBack.class)
public interface CodeProblemSampleApi {
    @GetMapping("/helloNacos")
    String helloNacos();
}
