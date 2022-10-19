package net.edu.module.api;



import io.swagger.v3.oas.annotations.Operation;
import net.edu.framework.common.utils.Result;
import net.edu.module.fallback.EduProblemFallBack;
import net.edu.module.fallback.EduTeachApiFallBack;
import net.edu.module.vo.EnrollUserVO;
import net.edu.module.vo.TeachPlanItemPaperVO;
import net.edu.module.vo.TeachPlanItemResourceVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "edu-cloud-teaching", fallbackFactory = EduTeachApiFallBack.class)
public interface EduTeachApi {

    /****************************Judge调用******************************************/
    @GetMapping("/student/updateSubmitCorrectTimes")
    Result<String> updateSubmitCorrectTimes(@RequestParam(value = "userId") Long userId, @RequestParam(value = "correct") Integer correct);


    /****************************Lessons调用******************************************/
    @GetMapping("planItem/paper/{id}")
    Result<List<TeachPlanItemPaperVO>> getItemPaper(@PathVariable(value = "id") Long id);

    @GetMapping("planItem/resource/{id}")
    Result<List<TeachPlanItemResourceVO>> getItemResource(@PathVariable(value = "id") Long id);


    @GetMapping("classUser/list/{classId}")
    Result<List<Long>> list(@PathVariable(value = "classId") Long classId);


    @GetMapping("class/updateNextLesson")
    @Operation(summary = "修改下一堂课id")
    Result<String> updateNextLesson(@RequestParam(value = "nextLesson") Long nextLesson, @RequestParam(value = "classId") Long classId);

    @GetMapping("/classUser/updateHomeworkTimes")
    @Operation(summary = "更新回家作业完成情况") Result<String>
    updateHomeworkTimes(@RequestParam(value = "userId") Long userId, @RequestParam(value = "classId") Long classId, @RequestParam(value = "num") Integer num );

    /****************************Quartz调用******************************************/
    @GetMapping("/statistics/info")
    @Operation(summary = "统计信息用于定时器")
    Result<String> statisticsHomeInfo();

    /****************************Wechat调用******************************************/

    @GetMapping("enrollUser/insertClassUser")
    @Operation(summary = "添加班级学生")
    Result<String> insertClassUser(@RequestParam(value = "classId")Integer classId,@RequestParam(value = "id") Integer id);

    @PostMapping("enrollUser/insertOpenId")
    @Operation(summary = "添加班级学生openId")
    Result<String> insertOpenId(@RequestParam(value = "openId")String openId,@RequestParam(value = "unionId")String unionId);

    @PostMapping("enrollUser/post")
    @Operation(summary = "修改班级学生")
    Result<String> post(EnrollUserVO enrollUserVO);
}
