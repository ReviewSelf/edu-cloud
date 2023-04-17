package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.TeachDestroyedLessonRecordConvert;
import net.edu.module.entity.TeachDestroyedLessonRecordEntity;
import net.edu.module.query.TeachDestroyedLessonRecordQuery;
import net.edu.module.service.TeachDestroyedLessonRecordService;
import net.edu.module.vo.TeachDestroyedLessonRecordListVO;
import net.edu.module.vo.TeachDestroyedLessonRecordVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* 消课管理
*
* @author sqw 
* @since 1.0.0 2023-03-04
*/
@RestController
@RequestMapping("destroyed/lesson/record")
@Tag(name="消课管理")
@AllArgsConstructor
public class TeachDestroyedLessonRecordController {
    private final TeachDestroyedLessonRecordService teachDestroyedLessonRecordService;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<TeachDestroyedLessonRecordVO>> page(@Valid TeachDestroyedLessonRecordQuery query){
        PageResult<TeachDestroyedLessonRecordVO> page = teachDestroyedLessonRecordService.page(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<TeachDestroyedLessonRecordVO> get(@PathVariable("id") Long id){
        TeachDestroyedLessonRecordEntity entity = teachDestroyedLessonRecordService.getById(id);

        return Result.ok(TeachDestroyedLessonRecordConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody TeachDestroyedLessonRecordListVO vo){
        teachDestroyedLessonRecordService.saveList(vo);
        return Result.ok();
    }
    @PostMapping("list")
    @Operation(summary = "保存")
    public Result<String> addRecord(@RequestBody List<TeachDestroyedLessonRecordVO> list){
        teachDestroyedLessonRecordService.addRecord(list);
        return Result.ok();
    }
    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid TeachDestroyedLessonRecordVO vo){
        teachDestroyedLessonRecordService.updateNew(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        teachDestroyedLessonRecordService.delete(idList);

        return Result.ok();
    }
}