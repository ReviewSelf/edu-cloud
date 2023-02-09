package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.TeachClassRecordConvert;
import net.edu.module.entity.TeachClassRecordEntity;
import net.edu.module.query.TeachClassRecordQuery;
import net.edu.module.service.TeachClassRecordService;
import net.edu.module.vo.TeachClassRecordVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* 学生班级记录
*
* @author sqw 
* @since 1.0.0 2023-02-08
*/
@RestController
@RequestMapping("class/record")
@Tag(name="学生班级记录")
@AllArgsConstructor
public class TeachClassRecordController {
    private final TeachClassRecordService teachClassRecordService;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<TeachClassRecordVO>> page(@Valid TeachClassRecordQuery query){
        PageResult<TeachClassRecordVO> page = teachClassRecordService.page(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<TeachClassRecordVO> get(@PathVariable("id") Long id){
        TeachClassRecordEntity entity = teachClassRecordService.getById(id);

        return Result.ok(TeachClassRecordConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody TeachClassRecordVO vo){
        teachClassRecordService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid TeachClassRecordVO vo){
        teachClassRecordService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        teachClassRecordService.delete(idList);

        return Result.ok();
    }
}