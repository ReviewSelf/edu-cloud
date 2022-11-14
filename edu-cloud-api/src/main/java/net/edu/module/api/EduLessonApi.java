package net.edu.module.api;



import io.swagger.v3.oas.annotations.Operation;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.framework.security.user.SecurityUser;
import net.edu.module.fallback.EduLessonApiFallBack;
import net.edu.module.vo.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(value = "edu-cloud-lessons", fallbackFactory = EduLessonApiFallBack.class)
public interface EduLessonApi {
    /****************************quartz调用******************************************/
    @GetMapping("/lesson/homework/deadline")
    Result<String> homeWorkDeadline();


    /****************************teaching调用******************************************/
    @DeleteMapping("/lesson")
    Result<String> delete(@RequestBody Long classId);


    /****************************WxMini调用******************************************/
    @GetMapping("/homeWork/homeWorkPage")
    Result<PageResult<HomeWorkVO>> getStudentHomeWorkPage(@RequestParam Long studentId, @RequestParam(value = "limit") Integer limit, @RequestParam(value = "page") Integer page);

    @GetMapping("/exam/studentPage")
    Result<PageResult<ExamVO>> studentPage(@RequestParam(value = "limit") Integer limit, @RequestParam(value = "page") Integer page,@RequestParam(value="userId")Long userId,@RequestParam(value="beginTime")String beginTime,@RequestParam(value="endTime")String endTime,@RequestParam(value = "status")String status);

    @GetMapping("/exam/problem/list/{examId}")
    Result<List<ExamProblemEntity>> getExamList(@PathVariable(value="examId") Long examId);



}
