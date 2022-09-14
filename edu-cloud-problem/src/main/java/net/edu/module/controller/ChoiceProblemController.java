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
import net.edu.module.vo.ChoiceOptionVO;
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
    public Result<ChoiceProblemVO> get(@PathVariable("id") Long id){
        ChoiceProblemVO vo = choiceProblemService.getChoiceProblem(id);
        return Result.ok(vo);
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody ChoiceProblemVO vo){
        choiceProblemService.save(vo);

        return Result.ok();
    }


    @GetMapping("updateStatus/{problemId}")
    @Operation(summary = "修改状态")
    public Result<String> updateStatus(@PathVariable("problemId")  Long problemId){
        choiceProblemService.updateStatus(problemId);

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

    @PutMapping("usedNum")
    @Operation(summary = "修改引用次数")
    public Result<String> updateUsedNum(@RequestParam Long id ){

        choiceProblemService.updateUsedNum(id);
        return Result.ok();
    }

    @GetMapping("submitTimes")
    @Operation(summary = "修改提交和正确次数")
    public Result<String> updateSubmitTimes(@RequestParam Long id , @RequestParam Boolean isTrue ){

        choiceProblemService.updateSubmitTimes(id,isTrue);
        return Result.ok();
    }

    @GetMapping("options/{problemId}")
    @Operation(summary = "获取选项")
    public List<String> getOptions(@PathVariable("problemId") Long problemId,@RequestParam(required = false)int flag){
        List<String> list = choiceProblemService.getChoiceOptions(problemId,flag);
        return list;
    }

    @PostMapping("choiceProblemInfo")
    @Operation(summary = "获取题目和选型")
     public Result<List<ChoiceProblemVO>> selectChoiceProblemInfo(@RequestBody List<Long> idList){
        return Result.ok(choiceProblemService.selectChoiceProblemInfo(idList));
    }

}