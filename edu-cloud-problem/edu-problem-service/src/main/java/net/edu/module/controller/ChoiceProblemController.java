package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.ChoiceProblemConvert;
import net.edu.module.entity.ChoiceProblemEntity;
import net.edu.module.service.ChoiceProblemService;
import net.edu.module.query.ChoiceProblemQuery;
import net.edu.module.vo.ChoiceProblemVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* 选择题库表
*
* @author 马佳浩 
* @since 1.0.0 2022-09-05
*/
@RestController
@RequestMapping("choice")
@Tag(name="选择题库表")
@AllArgsConstructor
public class ChoiceProblemController {
    private final ChoiceProblemService choiceProblemService;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<ChoiceProblemVO>> page(@Valid ChoiceProblemQuery query){
        PageResult<ChoiceProblemVO> page = choiceProblemService.page(query);
        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @PreAuthorize("hasAuthority('choiceproblem:info')")
    public Result<ChoiceProblemVO> get(@PathVariable("id") Long id){
        ChoiceProblemEntity entity = choiceProblemService.getById(id);
        return Result.ok(ChoiceProblemConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody ChoiceProblemVO vo){
        choiceProblemService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid ChoiceProblemVO vo){
        choiceProblemService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        choiceProblemService.delete(idList);

        return Result.ok();
    }
}