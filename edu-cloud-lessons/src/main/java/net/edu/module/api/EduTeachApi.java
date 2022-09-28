package net.edu.module.api;

import io.swagger.v3.oas.annotations.Operation;
import net.edu.framework.common.utils.Result;
import net.edu.module.api.fallback.EduTeachApiFallBack;
import net.edu.module.api.vo.TeachPlanItemPaperVO;
import net.edu.module.api.vo.TeachPlanItemResourceVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/19 9:33
 * @Version: 1.0
 * @Description:
 */
@FeignClient(value = "edu-cloud-teaching", fallbackFactory = EduTeachApiFallBack.class)
public interface EduTeachApi {

    @GetMapping("planItem/paper/{id}")
    Result<List<TeachPlanItemPaperVO>> getItemPaper(@PathVariable(value = "id") Long id);

    @GetMapping("planItem/resource/{id}")
    Result<List<TeachPlanItemResourceVO>> getItemResource(@PathVariable(value = "id") Long id);


    @GetMapping("classUser/list/{classId}")
     Result<List<Long>> list(@PathVariable(value = "classId") Long classId);


    @GetMapping("class/updateNextLesson")
    @Operation(summary = "修改下一堂课id")
    Result<String> updateNextLesson(@RequestParam Long nextLesson, @RequestParam Long classId);

}
