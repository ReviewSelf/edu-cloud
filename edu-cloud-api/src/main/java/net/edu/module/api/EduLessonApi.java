package net.edu.module.api;



import net.edu.framework.common.utils.Result;
import net.edu.module.fallback.EduLessonApiFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "edu-cloud-lessons", fallbackFactory = EduLessonApiFallBack.class)
public interface EduLessonApi {
    /****************************quartz调用******************************************/
    @GetMapping("/lesson/homework/deadline")
    Result<String> homeWorkDeadline();
}
