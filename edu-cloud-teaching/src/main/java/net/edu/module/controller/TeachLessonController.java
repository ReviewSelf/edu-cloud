package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.TeachLessonConvert;
import net.edu.module.entity.TeachLessonEntity;
import net.edu.module.service.TeachLessonService;
import net.edu.module.query.TeachLessonQuery;
import net.edu.module.vo.TeachLessonVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* 课程表
*
* @author 翁瑞辰 babamu@126.com
* @since 1.0.0 2022-09-09
*/
@RestController
@RequestMapping("teach/lesson")
@Tag(name="课程表")
@AllArgsConstructor
public class TeachLessonController {
    private final TeachLessonService teachLessonService;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<TeachLessonVO>> page(@Valid TeachLessonQuery query){
        PageResult<TeachLessonVO> page = teachLessonService.page(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<TeachLessonVO> get(@PathVariable("id") Long id){
        TeachLessonEntity entity = teachLessonService.getById(id);

        return Result.ok(TeachLessonConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody TeachLessonVO vo){
        teachLessonService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid TeachLessonVO vo){
        teachLessonService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        teachLessonService.delete(idList);

        return Result.ok();
    }
}