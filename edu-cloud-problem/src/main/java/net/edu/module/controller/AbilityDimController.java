package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.AbilityDimConvert;
import net.edu.module.convert.KnowledgePointConvert;
import net.edu.module.entity.AbilityDimEntity;
import net.edu.module.service.AbilityDimService;
import net.edu.module.query.AbilityDimQuery;
import net.edu.module.vo.AbilityDimVO;
import net.edu.module.vo.KnowledgePointVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* 能力模块表
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-25
*/
@RestController
@RequestMapping("dim")
@Tag(name="能力模块表")
@AllArgsConstructor
public class AbilityDimController {
    private final AbilityDimService abilityDimService;

    @GetMapping("list")
    @Operation(summary = "能力模块列表")
    public Result<List<AbilityDimVO>> list(){
        List<AbilityDimVO> list = abilityDimService.getAdList();
        return Result.ok(list);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<AbilityDimVO> get(@PathVariable("id") Long id){
        AbilityDimEntity entity = abilityDimService.getById(id);
        return Result.ok(AbilityDimConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody AbilityDimVO vo){
        abilityDimService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid AbilityDimVO vo){
        abilityDimService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        abilityDimService.delete(idList);

        return Result.ok();
    }
}