package net.edu.module.api;



import io.swagger.v3.oas.annotations.Operation;
import net.edu.framework.common.utils.Result;
import net.edu.module.fallback.EduLessonApiFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "edu-cloud-lessons", fallbackFactory = EduLessonApiFallBack.class)
public interface EduLessonApi {
    /****************************quartz调用******************************************/
    @GetMapping("/lesson/homework/deadline")
    Result<String> homeWorkDeadline();



    /****************************teaching调用******************************************/
    @DeleteMapping("/lesson")
    Result<String> delete(@RequestBody Long classId);
}
