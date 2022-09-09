package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;

import net.edu.module.convert.ProblemPaperConvert;
import net.edu.module.entity.ProblemPaperEntity;
import net.edu.module.service.ProblemPaperService;
import net.edu.module.query.ProblemPaperQuery;
import net.edu.module.vo.ProblemPaperVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* 问题卷表
*
* @author 樊磊 
* @since 1.0.0 2022-09-05
*/
@RestController
@RequestMapping("paper")
@Tag(name="问题卷表")
@AllArgsConstructor
public class ProblemPaperController {
    private final ProblemPaperService problemPaperService;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<ProblemPaperVO>> page(@Valid ProblemPaperQuery query){
        PageResult<ProblemPaperVO> page = problemPaperService.page(query);
        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<ProblemPaperVO> get(@PathVariable Long id){
        ProblemPaperEntity entity = problemPaperService.getById(id);
        return Result.ok(ProblemPaperConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody ProblemPaperVO vo){
        problemPaperService.save(vo);
        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid ProblemPaperVO vo){
        problemPaperService.update(vo);
        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        problemPaperService.delete(idList);
        return Result.ok();
    }

    @GetMapping("updateStatus/{paperId}")
    @Operation(summary = "修改状态")
    public Result<String> updateStatus(@PathVariable Long paperId){
        problemPaperService.updateStatus(paperId);
        return Result.ok();
    }
}