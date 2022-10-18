package net.edu.module.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.query.ExamRecordQuery;
import net.edu.module.service.ExamRecordService;
import net.edu.module.vo.exam.ExamScoreVO;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
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
     * 获取当前考生的答题记录
     * @param examId
     * @param userId
     * @return
     */
    @GetMapping("/getUserExamScore")
    public Result<ExamScoreVO> getUserExamScore(@RequestParam("examId") Long examId, @RequestParam(value = "userId") Long userId){
        return Result.ok(examRecordService.getUserExamScore(examId,userId));
    }

    /**
     * 获取考场中每个人每题的答题记录
     * @return
     */
    @GetMapping("/getExamRecordList")
    public Result<PageResult<ExamScoreVO>> getExamRecordList(@Valid ExamRecordQuery query ){
        return Result.ok(examRecordService.getExamRecordList(query));
    }


    /**
     * 一键批改
     */
    @GetMapping("/makePaper")
    public Result<PageResult<ExamScoreVO>> makePaper(@RequestParam("examId") Long examId, @RequestParam(value = "userId") Long userId ){
        examRecordService.makePaper(examId,userId);
        return Result.ok();
    }

    /**
     * 改分
     */
    @GetMapping("/changeProblemScore")
    public Result<PageResult<ExamScoreVO>> changeProblemScore(@RequestParam("score") BigDecimal score, @RequestParam(value = "recordId") Long recordId ){
        examRecordService.changeProblemScore(score,recordId);
        return Result.ok();
    }
}
