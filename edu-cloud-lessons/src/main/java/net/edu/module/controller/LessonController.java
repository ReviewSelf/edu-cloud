package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.LessonConvert;
import net.edu.module.entity.LessonAttendLogEntity;
import net.edu.module.entity.LessonEntity;
import net.edu.module.query.LessonAttendLogQuery;
import net.edu.module.service.LessonAttendLogService;
import net.edu.module.service.LessonService;
import net.edu.module.query.LessonQuery;
import net.edu.module.vo.LessonAttendLogVO;
import net.edu.module.vo.LessonVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 课程表
 *
 * @author 马佳浩
 * @since 1.0.0 2022-09-15
 */
@RestController
@RequestMapping("lesson")
@Tag(name = "课程表")
@AllArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    @GetMapping("page")
    @Operation(summary = "课程列表")
    public Result<PageResult<LessonVO>> page(@Valid LessonQuery query) {
        return Result.ok(lessonService.page(query));
    }

    @GetMapping("list")
    @Operation(summary = "课程列表")
    public Result<List<LessonVO>> list(@Valid LessonQuery query) {
        return Result.ok(lessonService.list(query));
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<LessonVO> get(@PathVariable("id") Long id) {
        LessonEntity entity = lessonService.getById(id);
        return Result.ok(LessonConvert.INSTANCE.convert(entity));
    }

    @PostMapping("/create")
    @Operation(summary = "创建课堂")
    public Result<String> createLessons(@RequestBody List<LessonVO> voList) {
        lessonService.createLessons(voList);
        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid LessonVO vo) {
        System.out.println(vo);
        lessonService.update(vo);
        return Result.ok();
    }



    @PutMapping("/homework")
    @Operation(summary = "回家作业修改")
    public Result<String> updateHomework(@RequestBody @Valid LessonVO vo) {
        System.out.println(vo);
        lessonService.updateHomework(vo);
        return Result.ok();
    }

    @GetMapping("homework/page")
    @Operation(summary = "课程列表")
    public Result<PageResult<LessonVO>> homeworkPage(@Valid LessonQuery query) {
        return Result.ok(lessonService.homeworkPage(query));
    }

}
