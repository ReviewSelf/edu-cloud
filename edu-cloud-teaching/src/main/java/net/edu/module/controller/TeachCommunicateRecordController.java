package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.TeachCommunicateRecordConvert;
import net.edu.module.entity.TeachCommunicateRecordEntity;
import net.edu.module.query.TeachCommunicateRecordQuery;
import net.edu.module.service.TeachCommunicateRecordService;
import net.edu.module.vo.TeachCommunicateRecordVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* 沟通记录表
*
* @author sqw 
* @since 1.0.0 2023-02-05
*/
@RestController
@RequestMapping("communicate")
@Tag(name="沟通记录表")
@AllArgsConstructor
public class TeachCommunicateRecordController {
    private final TeachCommunicateRecordService teachCommunicateRecordService;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<TeachCommunicateRecordVO>> page(@Valid TeachCommunicateRecordQuery query){
        PageResult<TeachCommunicateRecordVO> page = teachCommunicateRecordService.page(query);

        return Result.ok(page);
    }

//    @GetMapping("{id}")
//    @Operation(summary = "信息")
//    public Result<TeachCommunicateRecordVO> get(@PathVariable("id") Long id){
//        TeachCommunicateRecordEntity entity = teachCommunicateRecordService.getById(id);
//
//        return Result.ok(TeachCommunicateRecordConvert.INSTANCE.convert(entity));
//    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody TeachCommunicateRecordVO vo){
        teachCommunicateRecordService.save(vo);

        return Result.ok();
    }

//    @PutMapping
//    @Operation(summary = "修改")
//    public Result<String> update(@RequestBody @Valid TeachCommunicateRecordVO vo){
//        teachCommunicateRecordService.update(vo);
//
//        return Result.ok();
//    }

//    @DeleteMapping
//    @Operation(summary = "删除")
//    public Result<String> delete(@RequestBody List<Long> idList){
//        teachCommunicateRecordService.delete(idList);
//
//        return Result.ok();
//    }
}