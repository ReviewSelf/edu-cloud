package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.CodeProblemConvert;
import net.edu.module.entity.CodeProblemEntity;
import net.edu.module.service.CodeProblemService;
import net.edu.module.query.CodeProblemQuery;
import net.edu.module.vo.CodeProblemVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* 代码题库表
*
* @author 马佳浩 
* @since 1.0.0 2022-09-05
*/
@RestController
@RequestMapping("code")
@Tag(name="代码题库表")
@AllArgsConstructor
public class CodeProblemController {
    private final CodeProblemService codeProblemService;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<CodeProblemVO>> page(@Valid CodeProblemQuery query){
        PageResult<CodeProblemVO> page = codeProblemService.page(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<CodeProblemVO> get(@PathVariable("id") Long id){
        CodeProblemEntity entity = codeProblemService.getById(id);
        entity.setMemoryLimit(entity.getMemoryLimit()/1024);
        return Result.ok(CodeProblemConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody CodeProblemVO vo){
        codeProblemService.save(vo);

        return Result.ok();
    }

    @GetMapping("updateStatus/{id}")
    @Operation(summary = "修改状态")
    public Result<String> updateStatus(@PathVariable("id")  Integer id){
        codeProblemService.updateStatus(id);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid CodeProblemVO vo){
        vo.setMemoryLimit(vo.getMemoryLimit()*1024);
        codeProblemService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        codeProblemService.delete(idList);

        return Result.ok();
    }
}