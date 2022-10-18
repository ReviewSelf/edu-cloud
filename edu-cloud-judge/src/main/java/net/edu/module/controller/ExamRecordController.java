package net.edu.module.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.module.service.ExamRecordService;
import net.edu.module.vo.exam.ExamScoreVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 马佳浩
 * @Date: 2022/10/18 9:37
 * @Version: 1.0
 * @Description:
 */
@RestController
@Tag(name = "代码题判题接口")
@AllArgsConstructor
@RequestMapping("/exam/record")
public class ExamRecordController {

   private final   ExamRecordService examRecordService;

    /**
     * 获取考场中每个人每题的答题记录
     * @param examId
     * @param userId
     * @return
     */
    @GetMapping("/getExamScore")
    public Result<List<ExamScoreVO>> getExamScore(@RequestParam("examId") Long examId, @RequestParam(value = "userId") Long userId){
        return Result.ok(examRecordService.getExamScore(examId,userId));
    }


}
