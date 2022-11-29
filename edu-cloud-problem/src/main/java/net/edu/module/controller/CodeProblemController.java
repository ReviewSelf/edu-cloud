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
import net.edu.module.vo.CodeProblemAnswerVo;
import net.edu.module.vo.CodeProblemVO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
@Tag(name = "代码题库表")
@AllArgsConstructor
public class CodeProblemController {
    private final CodeProblemService codeProblemService;

    @GetMapping("page")
    @Operation(summary = "获取代码题数据并分页")
    public Result<PageResult<CodeProblemVO>> page(@Valid CodeProblemQuery query) {
        PageResult<CodeProblemVO> page = codeProblemService.page(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "获取代码题原有信息")
    public Result<CodeProblemVO> get(@PathVariable("id") Long id) {
        CodeProblemEntity entity = codeProblemService.getById(id);
        entity.setMemoryLimit(entity.getMemoryLimit() / 1024);
        return Result.ok(CodeProblemConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "新增代码题时保存")
    public Result<String> save(@RequestBody CodeProblemVO vo) {
        vo.setMemoryLimit(vo.getMemoryLimit() * 1024);
        codeProblemService.save(vo);

        return Result.ok();
    }

    @GetMapping("updateStatus/{problemId}")
    @Operation(summary = "发布和下架")
    public Result<String> updateStatus(@PathVariable("problemId") Long problemId) {
        codeProblemService.updateStatus(problemId);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid CodeProblemVO vo) {
        if(vo.getMemoryLimit()!= null){
            vo.setMemoryLimit(vo.getMemoryLimit() * 1024);
        }
        codeProblemService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList) {
        codeProblemService.delete(idList);

        return Result.ok();
    }

    @GetMapping("usedNum")
    @Operation(summary = "修改引用次数")
    public Result<String> updateUsedNum(@RequestParam Long id) {

        codeProblemService.updateUsedNum(id);
        return Result.ok();
    }

    @GetMapping("submitTimes")
    @Operation(summary = "修改提交和正确次数")
    public Result<String> updateSubmitTimesFromJudge(@RequestParam Long id, @RequestParam Boolean isTrue) {
        codeProblemService.updateSubmitTimes(id, isTrue);
        return Result.ok();
    }


    @GetMapping("problemInfo/{problemId}")
    @Operation(summary = "获取答题题目信息")
    public Result<CodeProblemVO> getCodeProblemInfo(@PathVariable("problemId") Long problemId) {
        return Result.ok(codeProblemService.getCodeProblemInfo(problemId));
    }

    @GetMapping("getCodeAnswer/{problemId}")
    @Operation(summary = "获取代码题答案")
    public Result<CodeProblemAnswerVo> getCodeAnswer(@PathVariable("problemId") Long problemId) {
        return Result.ok(codeProblemService.getCodeProblemAnswer(problemId));
    }


    @PostMapping("/import")
    public Result<String> importFromExcel(@RequestParam("file") MultipartFile file) {
        codeProblemService.importFromExcel(file);
        return Result.ok();
    }

    @PostMapping("/importZipFile")
    public Result<String> importZipFile(@RequestParam("zipFile") MultipartFile zipFile) {
        return codeProblemService.importZipFile(zipFile);
    }

}
