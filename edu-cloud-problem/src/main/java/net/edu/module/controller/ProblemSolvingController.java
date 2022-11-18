package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.framework.security.user.SecurityUser;
import net.edu.module.query.ProblemSolvingQuery;
import net.edu.module.service.ProblemSolvingService;
import net.edu.module.vo.ProblemSolvingVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("solving")
@Tag(name="问题题解表")
@AllArgsConstructor
public class ProblemSolvingController {
    private final ProblemSolvingService problemSolvingService;

    @GetMapping("/page")
    @Operation(summary = "获取题目资源")
    public Result<PageResult<ProblemSolvingVO>> getSolvingList(@Valid ProblemSolvingQuery query) {
        PageResult<ProblemSolvingVO> list = problemSolvingService.getSolvingList(query);
        return Result.ok(list);
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取题解内容")
    public Result<ProblemSolvingVO> getProblemSolving(@PathVariable("id") Long id) {
        ProblemSolvingVO vo = problemSolvingService.getProblemSolving(id);
        return Result.ok(vo);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除题解")
    public Result<String> deleteProblemSolving(@PathVariable("id") Long id) {
        problemSolvingService.deleteProblemSolving(id);
        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> updateProblemSolving(@RequestBody ProblemSolvingVO vo){
        problemSolvingService.updateProblemSolving(vo);
        return Result.ok();
    }

    @PostMapping
    @Operation(summary = "保存题解")
    public Result<String> saveProblemSolving(@RequestBody ProblemSolvingVO vo) {
        problemSolvingService.saveProblemSolving(vo);
        return Result.ok();
    }
}
