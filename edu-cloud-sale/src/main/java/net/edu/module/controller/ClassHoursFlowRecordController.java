package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.ClassHoursFlowRecordConvert;
import net.edu.module.entity.ClassHoursFlowRecordEntity;
import net.edu.module.query.ClassHoursFlowRecordQuery;
import net.edu.module.service.ClassHoursFlowRecordService;
import net.edu.module.vo.ClassHoursFlowRecordVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* 课时流水表
*
* @author sqw 
* @since 1.0.0 2023-02-15
*/
@RestController
@RequestMapping("flow/record")
@Tag(name="课时流水表")
@AllArgsConstructor
public class ClassHoursFlowRecordController {
    private final ClassHoursFlowRecordService classHoursFlowRecordService;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<ClassHoursFlowRecordVO>> page(@Valid ClassHoursFlowRecordQuery query){
        PageResult<ClassHoursFlowRecordVO> page = classHoursFlowRecordService.page(query);
        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<ClassHoursFlowRecordVO> get(@PathVariable("id") Long id){
        ClassHoursFlowRecordEntity entity = classHoursFlowRecordService.getById(id);

        return Result.ok(ClassHoursFlowRecordConvert.INSTANCE.convert(entity));
    }




    @PostMapping()
    @Operation(summary = "保存流水记录")
    public Result<String> saveFlowRecord(@RequestBody ClassHoursFlowRecordVO vo){
        classHoursFlowRecordService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid ClassHoursFlowRecordVO vo){
        classHoursFlowRecordService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        classHoursFlowRecordService.delete(idList);

        return Result.ok();
    }
}