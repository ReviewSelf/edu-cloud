package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.module.service.AbilityPointService;
import net.edu.module.vo.AbilityPointVO;
import net.edu.module.vo.AbilityRelatedVO;
import org.springframework.web.bind.annotation.*;
import net.edu.module.vo.AbilityBatchImportVO;
import javax.validation.Valid;
import java.util.List;

/**
 * 能力纬度点管理
 *
 * @author sqw
 * @since 1.0.0 2022-10-27
 */
@RestController
@RequestMapping("ability/point")
@Tag(name="能力点管理")
@AllArgsConstructor
public class AbilityPointController {

    private final AbilityPointService abilityPointService;

    @GetMapping("list/{id}")
    @Operation(summary = "获取能力点列表")
    public Result<List<AbilityPointVO>> list(@PathVariable("id") Long id){
        List<AbilityPointVO> list = abilityPointService.getList(id);

        return Result.ok(list);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<AbilityPointVO> get(@PathVariable("id") Long id){
        AbilityPointVO vo = abilityPointService.getPointInfo(id);
        return Result.ok(vo);
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody @Valid AbilityBatchImportVO vo){
        abilityPointService.save(vo.getList(),vo.getId());
        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid AbilityPointVO vo){
        abilityPointService.update(vo);
        return Result.ok();
    }

    @PutMapping("list")
    @Operation(summary = "一次修改多个")
    public Result<String> updateList(@RequestBody @Valid List<AbilityPointVO> list){
        abilityPointService.updateList(list);
        return Result.ok();
    }

    @DeleteMapping("{id}")
    @Operation(summary = "删除")
    public Result<String> delete(@PathVariable("id") Long id){
        abilityPointService.delete(id);
        return Result.ok();
    }

    @GetMapping("related/list/{id}")
    @Operation(summary = "获取能力点关系列表")
    public Result<List<AbilityPointVO>> relatedList(@PathVariable("id") Long id){
        List<AbilityPointVO> list = abilityPointService.relatedList(id);

        return Result.ok(list);
    }

    @PostMapping("related")
    @Operation(summary = "保存点关系")
    public Result<String> saveRelated(@RequestBody @Valid AbilityRelatedVO vo){
        abilityPointService.saveRelated(vo);
        return Result.ok();
    }

    @DeleteMapping("related/{id}")
    @Operation(summary = "删除点关系")
    public Result<String> deleteRelated(@PathVariable("id") Long id){
        abilityPointService.deleteRelated(id);
        return Result.ok();
    }
}
