package net.edu.module.api;



import io.swagger.v3.oas.annotations.Operation;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.fallback.EduLessonApiFallBack;
import net.edu.module.vo.HomeWorkQuery;
import net.edu.module.vo.HomeWorkVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(value = "edu-cloud-lessons", fallbackFactory = EduLessonApiFallBack.class)
public interface EduLessonApi {
    /****************************quartz调用******************************************/
    @GetMapping("/lesson/homework/deadline")
    Result<String> homeWorkDeadline();



    /****************************teaching调用******************************************/
    @DeleteMapping("/lesson")
    Result<String> delete(@RequestBody Long classId);


    /****************************WxMini调用******************************************/
    @PostMapping("/homeWork/homeWorkPage")
    Result<PageResult<HomeWorkVO>> getStudentHomeWorkPage(@RequestBody HomeWorkQuery query);
}
