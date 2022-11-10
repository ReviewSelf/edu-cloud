package net.edu.module.controller;

import cn.hutool.json.JSONObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.framework.security.user.SecurityUser;
import net.edu.module.service.LessonRecordService;
import net.edu.module.vo.JudgeRecordSubmitVO;
import net.edu.module.vo.ProblemFinishVo;
import net.edu.module.vo.lesson.LessonJudgeRecordVo;
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



    @GetMapping("/getLessonProblemRecord")
    public Result<List<LessonJudgeRecordVo>> getLessonProblemRecord(@RequestParam("lessonId") Long lessonId, @RequestParam(value = "type",required = false) Integer type){
        return Result.ok(lessonRecordService.getLessonProblemRecord(lessonId,type));
    }

    @GetMapping("/getLessonProblemRank")
    @Operation(summary = "获取课堂答题排名")
    public Result<List<LessonProblemRankVO>> getLessonProblemRank(@RequestParam("lessonId") Long lessonId,
                                                                  @RequestParam(value = "type")Integer type){
        return Result.ok(lessonRecordService.getLessonProblemRank(lessonId,type));
    }

    @PostMapping("/getUserLessonRecord")
    @Operation(summary = "获取学生每个课堂答题情况")
    public Result<List<LessonProblemRankVO>> getUserLessonRecord(@RequestBody JSONObject jsonObject){
        List<Long> lessonId=jsonObject.getBeanList("lessonList",Long.class);
        Long userId=jsonObject.getLong("userId");
        Integer type=jsonObject.getInt("type");
        return Result.ok(lessonRecordService.getUserLessonRecord(lessonId,userId,type));
    }


    @GetMapping("/getProblemFinish")
    @Operation(summary = "获取已完成答题")
    public Result<List<ProblemFinishVo>> getProblemFinish(@RequestParam("lessonId") Long lessonId,
                                                          @RequestParam(value = "type")Integer type){
        Long userId= SecurityUser.getUserId();

        return Result.ok(lessonRecordService.getProblemFinish(lessonId,userId,type));
    }

}
