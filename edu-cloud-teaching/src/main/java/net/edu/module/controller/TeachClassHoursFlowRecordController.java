package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.TeachClassHoursFlowRecordConvert;
import net.edu.module.entity.TeachClassHoursFlowRecordEntity;
import net.edu.module.query.TeachClassHoursFlowRecordQuery;
import net.edu.module.service.TeachClassHoursFlowRecordService;
import net.edu.module.vo.TeachClassHoursFlowRecordVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* 课时流水管理
*
* @author sqw 
* @since  2023-03-06
*/
@RestController
@RequestMapping("class/hours/flow/record")
@Tag(name="课时流水管理")
@AllArgsConstructor
public class TeachClassHoursFlowRecordController {
    private final TeachClassHoursFlowRecordService teachClassHoursFlowRecordService;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<TeachClassHoursFlowRecordVO>> page(@Valid TeachClassHoursFlowRecordQuery query){
        PageResult<TeachClassHoursFlowRecordVO> page = teachClassHoursFlowRecordService.page(query);

        return Result.ok(page);
    }

    @GetMapping
    @Operation(summary = "信息")
    public Result<TeachClassHoursFlowRecordVO> get(@RequestParam("lessonId") Long lessonId,@RequestParam("stuId") Long stuId){
        TeachClassHoursFlowRecordEntity entity = teachClassHoursFlowRecordService.getByLessonIdAndStudId(lessonId,stuId);

        return Result.ok(TeachClassHoursFlowRecordConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody TeachClassHoursFlowRecordVO vo){
        teachClassHoursFlowRecordService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid TeachClassHoursFlowRecordVO vo){
        teachClassHoursFlowRecordService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        teachClassHoursFlowRecordService.delete(idList);

        return Result.ok();
    }
}