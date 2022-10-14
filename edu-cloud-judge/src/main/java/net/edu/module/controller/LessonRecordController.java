package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.module.service.LessonRecordService;
import net.edu.module.vo.JudgeRecordSubmitVO;
import net.edu.module.vo.lesson.LessonProblemRankVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 马佳浩
 * @Date: 2022/10/14 20:24
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping("/lesson/record")
@AllArgsConstructor
public class LessonRecordController {

    private final LessonRecordService lessonRecordService;

    @GetMapping("/getLessonProblemRank")
    @Operation(summary = "获取课堂答题排名")
    public Result<List<LessonProblemRankVO>> getLessonProblemRank(@RequestParam("lessonId") Long lessonId,
                                                                  @RequestParam(value = "type")Integer type){
        return Result.ok(lessonRecordService.getLessonProblemRank(lessonId,type));
    }
}
