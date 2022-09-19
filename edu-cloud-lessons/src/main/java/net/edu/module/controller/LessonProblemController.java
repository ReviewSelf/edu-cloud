package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.LessonProblemConvert;
import net.edu.module.entity.LessonProblemEntity;
import net.edu.module.service.LessonProblemService;
import net.edu.module.query.LessonProblemQuery;
import net.edu.module.vo.LessonProblemVO;
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
public class LessonProblemController {
    private final LessonProblemService lessonProblemService;

    @GetMapping("list")
    @Operation(summary = "获取课堂就题目信息")
    public Result<List<LessonProblemVO>> list(@Valid LessonProblemQuery query){
        List<LessonProblemVO> page = lessonProblemService.list(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<LessonProblemVO> get(@PathVariable("id") Long id){
        LessonProblemEntity entity = lessonProblemService.getById(id);

        return Result.ok(LessonProblemConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody LessonProblemVO vo){
        lessonProblemService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid LessonProblemVO vo){
        lessonProblemService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        lessonProblemService.delete(idList);

        return Result.ok();
    }
}