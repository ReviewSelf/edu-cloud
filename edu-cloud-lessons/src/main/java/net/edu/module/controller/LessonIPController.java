package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.LessonIPConvert;
import net.edu.module.entity.LessonIPEntity;
import net.edu.module.service.LessonIPService;
import net.edu.module.query.LessonIPQuery;
import net.edu.module.vo.LessonIPVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* 1
*
* @author 马佳浩 
* @since 1.0.0 2022-09-15
*/
@RestController
@RequestMapping("ip")
@Tag(name="课堂ip管理")
@AllArgsConstructor
public class LessonIPController {
    private final LessonIPService lessonIpService;

    @GetMapping("list")
    @Operation(summary = "ip列表")
    public Result<List<LessonIPVO>> list(@Valid LessonIPQuery query){
        List<LessonIPVO> list = lessonIpService.list(query);

        return Result.ok(list);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<LessonIPVO> get(@PathVariable("id") Long id){
        LessonIPEntity entity = lessonIpService.getById(id);

        return Result.ok(LessonIPConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody LessonIPVO vo){
        lessonIpService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid LessonIPVO vo){
        lessonIpService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        lessonIpService.delete(idList);

        return Result.ok();
    }


}