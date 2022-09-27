package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;

import net.edu.module.service.ProblemResourceService;
import net.edu.module.vo.ProblemResourceVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* 问题资源表
*
* @author 周建超 
* @since 1.0.0 2022-09-20
*/
@RestController
@RequestMapping("resource")
@Tag(name="问题资源表")
@AllArgsConstructor
public class ProblemResourceController {
    private final ProblemResourceService problemResourceService;


    @GetMapping("{id}")
    @Operation(summary = "获取题目资源")
    public Result<List<ProblemResourceVO>> getProblemResource(@PathVariable("id") Long id) {
        List<ProblemResourceVO> list = problemResourceService.getProblemResource(id);
        return Result.ok(list);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "删除题目资源")
    public Result<String> deleteProblemResource(@PathVariable("id") Long id) {
        problemResourceService.deleteProblemResource(id);

        return Result.ok();
    }

    @PostMapping("resource")
    @Operation(summary = "保存题目资源")
    public Result<String> saveProblemResource(@RequestBody ProblemResourceVO vo) {
        problemResourceService.saveProblemResource(vo);
        return Result.ok();
    }

}