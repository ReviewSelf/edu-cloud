package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.TeachPlanConvert;
import net.edu.module.entity.TeachPlanEntity;
import net.edu.module.query.TeachPlanQuery;
import net.edu.module.service.TeachPlanService;
import net.edu.module.vo.TeachPlanVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* 教学计划表
*
* @author sqw
* @since 1.0.0 2022-09-12
*/
@RestController
@RequestMapping("plan")
@Tag(name="教学计划表")
@AllArgsConstructor

public class TeachPlanController {
    private final TeachPlanService teachPlanService;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<TeachPlanVO>> page(@Valid TeachPlanQuery query){
        PageResult<TeachPlanVO> page = teachPlanService.page(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<TeachPlanVO> get(@PathVariable("id") Long id){
        TeachPlanEntity entity = teachPlanService.getById(id);

        return Result.ok(TeachPlanConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody TeachPlanVO vo){
        teachPlanService.save(vo);

        return Result.ok();
    }

    @GetMapping("updateStatus/{id}")
    @Operation(summary = "修改状态")
    public Result<String> updateStatus(@PathVariable("id")  Long id){
        teachPlanService.updateStatus(id);

        return Result.ok();
    }

    @PutMapping("usedNum")
    @Operation(summary = "修改引用次数")
    public Result<String> updateUsedNum(@RequestParam Long id ){

        teachPlanService.updateUsedNum(id);
        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@Valid @RequestBody TeachPlanVO vo){
        teachPlanService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        teachPlanService.delete(idList);

        return Result.ok();
    }

    @GetMapping("all")
    @Operation(summary = "所有计划")
    public Result<List<TeachPlanVO>> getAllByStatusTeachPlanVos(){
        List<TeachPlanVO> planVos = teachPlanService.getAllByStatusTeachPlanVos();
        return Result.ok(planVos);
    }


}
