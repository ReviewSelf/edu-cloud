package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.LessonResourceConvert;
import net.edu.module.entity.LessonResourceEntity;
import net.edu.module.service.LessonResourceService;
import net.edu.module.query.LessonResourceQuery;
import net.edu.module.vo.LessonResourceVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* 教学日历资源表
*
* @author 马佳浩 babamu@126.com
* @since 1.0.0 2022-09-15
*/
@RestController
@RequestMapping("resource")
@Tag(name="教学日历资源表")
@AllArgsConstructor
public class LessonResourceController {
    private final LessonResourceService lessonResourceService;


    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody LessonResourceVO vo){
        lessonResourceService.save(vo);

        return Result.ok();
    }

    @DeleteMapping("{id}")
    @Operation(summary = "删除")
    public Result<String> deleteResource(@PathVariable("id") Long id){
        lessonResourceService.deleteResource(id);

        return Result.ok();
    }


    @GetMapping("getLessonResource/{lessonId}")
    @Operation(summary = "通过课程id获取资源列表")
    public Result<List<LessonResourceVO>> getLessonResource(@PathVariable("lessonId") Long lessonId){
        return Result.ok(lessonResourceService.getLessonResource(lessonId));
    }
}