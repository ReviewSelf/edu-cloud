package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.ExamProblemConvert;
import net.edu.module.entity.ExamProblemEntity;
import net.edu.module.query.ExamProblemQuery;
import net.edu.module.service.ExamProblemService;
import net.edu.module.vo.ExamAttendLogVO;
import net.edu.module.vo.ExamProblemVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* 课堂练习表
*
* @author 马佳浩 
* @since 1.0.0 2022-09-15
*/
@RestController
@RequestMapping("exam/problem")
@Tag(name="课堂练习表")
@AllArgsConstructor
public class ExamProblemController {
    private final ExamProblemService examProblemService;



    @GetMapping("list/{examId}")
    @Operation(summary = "获取考试题目信息")
    public Result<List<ExamProblemEntity>> list(@PathVariable("examId") Long examId){

        return Result.ok(examProblemService.list(examId));
    }





}