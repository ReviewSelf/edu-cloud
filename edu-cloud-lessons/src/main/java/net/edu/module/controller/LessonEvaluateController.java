package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.LessonEvaluateConvert;
import net.edu.module.entity.LessonEvaluateEntity;
import net.edu.module.query.LessonEvaluateQuery;
import net.edu.module.service.LessonEvaluateService;
import net.edu.module.vo.LessonEvaluateVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* 课堂评价
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-18
*/
@RestController
@RequestMapping("evaluate")
@Tag(name="课堂评价")
@AllArgsConstructor
public class LessonEvaluateController {
    private final LessonEvaluateService lessonEvaluateService;


    @GetMapping("list/{lessonId}")
    @Operation(summary = "列表")
    public Result<List<LessonEvaluateEntity>> list(@PathVariable("lessonId") Long lessonId){
        return Result.ok(lessonEvaluateService.list(lessonId));
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<LessonEvaluateVO> get(@PathVariable("id") Long id){
        LessonEvaluateEntity entity = lessonEvaluateService.getById(id);

        return Result.ok(LessonEvaluateConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody List<LessonEvaluateVO> list){
        lessonEvaluateService.save(list);
        return Result.ok();
    }

    @PostMapping ("updateEvaluation")
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody LessonEvaluateVO vo){
        lessonEvaluateService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        lessonEvaluateService.delete(idList);

        return Result.ok();
    }
}