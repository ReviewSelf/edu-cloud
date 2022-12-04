package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.service.ChoiceProblemService;
import net.edu.module.query.ChoiceProblemQuery;
import net.edu.module.vo.ChoiceProblemVO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
@Tag(name = "选择题库表")
@AllArgsConstructor
public class ChoiceProblemController {
    private final ChoiceProblemService choiceProblemService;


    @GetMapping("page")
    @Operation(summary = "获取选择题数据并分页")
    public Result<PageResult<ChoiceProblemVO>> page(@Valid ChoiceProblemQuery query) {
        PageResult<ChoiceProblemVO> page = choiceProblemService.page(query);
        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "修改选择题时获取原有信息")
    public Result<ChoiceProblemVO> get(@PathVariable("id") Long id) {
        System.out.println(id);
        ChoiceProblemVO vo = choiceProblemService.getChoiceProblem(id);
        return Result.ok(vo);
    }

    @PostMapping
    @Operation(summary = "新增选择题时保存")
    public Result<String> save(@RequestBody ChoiceProblemVO vo) {
        choiceProblemService.save(vo);
        return Result.ok();
    }


    @GetMapping("updateStatus/{problemId}")
    @Operation(summary = "发布和下架选择题")
    public Result<String> updateStatus(@PathVariable("problemId") Long problemId) {
        choiceProblemService.updateStatus(problemId);

        return Result.ok();
    }


    @PutMapping
    @Operation(summary = "修改选择题信息")
    public Result<String> update(@RequestBody @Valid ChoiceProblemVO vo) {
        choiceProblemService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList) {
        choiceProblemService.delete(idList);

        return Result.ok();
    }

    @PutMapping("usedNum")
    @Operation(summary = "修改引用次数")
    public Result<String> updateUsedNum(@RequestParam Long id) {

        choiceProblemService.updateUsedNum(id);
        return Result.ok();
    }

    @GetMapping("submitTimes")
    @Operation(summary = "修改提交和正确次数")
    public Result<String> updateSubmitTimesFromJudge(@RequestParam Long id, @RequestParam Boolean isTrue) {

        choiceProblemService.updateSubmitTimes(id, isTrue);
        return Result.ok();
    }

    @GetMapping("options/{problemId}")
    @Operation(summary = "获取选项")
    public Result<List<String>> getOptionsFromJudge(@PathVariable("problemId") Long problemId, @RequestParam(required = false,defaultValue = "1")  int flag) {
        return Result.ok(choiceProblemService.getChoiceOptions(problemId, flag));
    }

    @GetMapping("problemInfo/{problemId}")
    @Operation(summary = "获取答题题目信息")
    public Result<ChoiceProblemVO> getChoiceProblemInfo(@PathVariable("problemId") Long problemId) {
        return Result.ok(choiceProblemService.getChoiceProblemInfo(problemId));
    }


    @PostMapping("/import")
    public Result<String> importFromExcel(@RequestParam("file") MultipartFile file) {
        choiceProblemService.importFromExcel(file);
        return Result.ok();
    }




}
