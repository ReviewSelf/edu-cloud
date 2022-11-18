package net.edu.module.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.module.service.TeachPlanPaperService;
import net.edu.module.vo.TeachPlanPaperVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("paper")
@Tag(name="教学计划关联考试表")
@AllArgsConstructor
public class TeachPlanPaperController {
    private final TeachPlanPaperService teachPlanPaperService;

    @PostMapping("addTeachPlanPaper")
    public Result<String> addTeachPlanPaper(@RequestBody List<TeachPlanPaperVO> teachPlanPaperList){
        teachPlanPaperService.addTeachPlanPaper(teachPlanPaperList);
        return Result.ok();
    }


    @GetMapping("getTeachPlanPaper")
    public Result<List<TeachPlanPaperVO>> getTeachPlanPaper(@RequestParam(value = "planId") Long planId){
        return Result.ok(teachPlanPaperService.getTeachPlanPaper(planId));
    }


    @GetMapping("deleteTeachPlanPaper")
    public Result<String> deleteTeachPlanPaper(@RequestParam(value = "planId") Long planId){

        teachPlanPaperService.deleteTeachPlanPaper(planId);
        return Result.ok();
    }

    @PostMapping("/getPaperByClassIdList")
    public Result<List<TeachPlanPaperVO>> getPaperByClassIdList(@RequestBody List<Long> classIdList) {
        return Result.ok(teachPlanPaperService.getPaperByClassIdList(classIdList));
    }

}
