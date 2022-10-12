package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.ExamProblemConvert;
import net.edu.module.entity.ExamProblemEntity;
import net.edu.module.query.ExamProblemQuery;
import net.edu.module.service.ExamProblemService;
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
@RequestMapping("problem")
@Tag(name="课堂练习表")
@AllArgsConstructor
public class ExamProblemController {
    private final ExamProblemService examProblemService;

    @GetMapping("list")
    @Operation(summary = "获取考试题目信息")
    public Result<List<ExamProblemVO>> list(@Valid ExamProblemQuery query){
        List<ExamProblemVO> page = examProblemService.list(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<ExamProblemVO> get(@PathVariable("id") Long id){
        ExamProblemEntity entity = examProblemService.getById(id);

        return Result.ok(ExamProblemConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody ExamProblemVO vo){
        examProblemService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid ExamProblemVO vo){
        examProblemService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        examProblemService.delete(idList);

        return Result.ok();
    }

    @PostMapping("/updateProblemTime")
    @Operation(summary = "更新课堂练习开始时间结束时间")
    public Result<String> updateProblemTime(@RequestBody List<ExamProblemVO> examProblemList){
        examProblemService.updateProblemTime(examProblemList);
        return Result.ok();
    }

    @PostMapping("/batchInsertExamProblem")
    @Operation(summary = "批量新增课堂题")
    public Result<String> batchInsertExamProblem(@RequestBody Object obj){

        return Result.ok();
    }



}