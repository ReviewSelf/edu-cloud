package net.edu.module.api;



import io.swagger.v3.oas.annotations.Operation;
import net.edu.framework.common.utils.Result;
import net.edu.module.fallback.EduProblemFallBack;
import net.edu.module.vo.TeachPlanItemPaperVO;
import net.edu.module.vo.TeachPlanItemResourceVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "edu-cloud-teaching", fallbackFactory = EduProblemFallBack.class)
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
}
