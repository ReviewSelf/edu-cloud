package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.AbilityConvert;
import net.edu.module.entity.AbilityEntity;
import net.edu.module.query.AbilityQuery;
import net.edu.module.service.AbilityService;
import net.edu.module.vo.AbilityListVO;
import net.edu.module.vo.AbilityVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* 能力纬度图
*
* @author sqw 
* @since 1.0.0 2022-10-27
*/
@RestController
@RequestMapping("ability")
@Tag(name="能力纬度图")
@AllArgsConstructor
public class AbilityController {
    private final AbilityService abilityService;



    @GetMapping("list")
    public Result<List<AbilityVO>> getAbilityList(){
        return Result.ok(abilityService.getAbilityList());
    }


    @GetMapping("item/list/{id}")
    public Result<List<AbilityVO>> getAbilityItemList(@PathVariable("id") Long id){
        return Result.ok(abilityService.getAbilityItemList(id));
    }


    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<AbilityVO> get(@PathVariable("id") Long id){
        AbilityEntity entity = abilityService.getById(id);

        return Result.ok(AbilityConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody AbilityVO vo){
        abilityService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid AbilityVO vo){
        abilityService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        abilityService.delete(idList);

        return Result.ok();
    }
}