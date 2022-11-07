package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.framework.security.user.SecurityUser;
import net.edu.module.service.AbilityPointService;
import net.edu.module.vo.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 能力纬度点管理
 *
 * @author sqw
 * @since 1.0.0 2022-10-27
 */
@RestController
@RequestMapping("/ability/map")
@Tag(name="能力图管理")
@AllArgsConstructor
public class AbilityPointController {

    private final AbilityPointService abilityPointService;

    @GetMapping("/{id}")
    @Operation(summary = "获取能力点列表")
    public Result<AbilityMapVO> getAbilityMap(@PathVariable("id") Long id){
        return Result.ok(abilityPointService.getAbilityMap(id,null));
    }

    @GetMapping("user/{id}")
    public Result<AbilityMapVO> getUserAbilityMap(@PathVariable("id") Long id){
        return Result.ok(abilityPointService.getAbilityMap(id, SecurityUser.getUserId()));
    }

    @PostMapping("/point")
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody @Valid AbilityBatchImportVO vo){
        abilityPointService.save(vo.getList(),vo.getId());
        return Result.ok();
    }

    @PutMapping("/point")
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid AbilityPointVO vo){
        abilityPointService.update(vo);
        return Result.ok();
    }

    @PutMapping("/point/list")
    @Operation(summary = "一次修改多个")
    public Result<String> updateList(@RequestBody @Valid List<AbilityPointVO> list){
        abilityPointService.updateList(list);
        return Result.ok();
    }

    @DeleteMapping("/point/{id}")
    @Operation(summary = "删除")
    public Result<String> delete(@PathVariable("id") Long id){
        abilityPointService.delete(id);
        return Result.ok();
    }


    @PostMapping("/point/related")
    @Operation(summary = "保存点关系")
    public Result<String> saveRelated(@RequestBody @Valid AbilityRelatedVO vo){
        abilityPointService.saveRelated(vo);
        return Result.ok();
    }

    @DeleteMapping("/point/related/{id}")
    @Operation(summary = "删除点关系")
    public Result<String> deleteRelated(@PathVariable("id") Long id){
        abilityPointService.deleteRelated(id);
        return Result.ok();
    }
}
