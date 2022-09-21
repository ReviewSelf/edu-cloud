package net.edu.module.api;


import io.swagger.v3.oas.annotations.Operation;
import net.edu.framework.common.utils.Result;
import net.edu.module.api.fallback.EduProblemFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "edu-cloud-teaching", fallbackFactory = EduProblemFallBack.class)
public interface EduTeachApi {
    @GetMapping("teaching/student/updateSubmitCorrectTimes")
    Result<String> updateSubmitCorrectTimes(@RequestParam Long userId, @RequestParam Integer correct);
}
