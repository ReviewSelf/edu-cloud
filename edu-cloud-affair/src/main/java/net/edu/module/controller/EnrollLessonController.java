package net.edu.module.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.query.EnrollLessonQuery;
import net.edu.module.query.EnrollUserQuery;
import net.edu.module.service.EnrollLessonService;
import net.edu.module.vo.EnrollLessonVO;
import net.edu.module.vo.EnrollUserVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("enrollLesson")
@Tag(name="试听课程")
@AllArgsConstructor
public class EnrollLessonController {

    private final EnrollLessonService enrollLessonService;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<EnrollLessonVO>> page(@Valid EnrollLessonQuery query){
        PageResult<EnrollLessonVO> page = enrollLessonService.page(query);
        return Result.ok(page);
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody EnrollLessonVO vo){
        System.out.println(vo);
        enrollLessonService.save(vo);
        return Result.ok();
    }

}
