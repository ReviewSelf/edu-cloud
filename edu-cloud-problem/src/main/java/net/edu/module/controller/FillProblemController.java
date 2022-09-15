package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.FillProblemConvert;
import net.edu.module.entity.FillProblemEntity;
import net.edu.module.service.FillProblemService;
import net.edu.module.query.FillProblemQuery;
import net.edu.module.vo.FillProblemVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* 填空题表
*
* @author 马佳浩 
* @since 1.0.0 2022-09-05
*/
@RestController
@RequestMapping("fill")
@Tag(name="填空题表")
@AllArgsConstructor
public class FillProblemController {
    private final FillProblemService fillProblemService;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<FillProblemVO>> page(@Valid FillProblemQuery query){
        PageResult<FillProblemVO> page = fillProblemService.page(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<FillProblemVO> get(@PathVariable("id") Long id){
        FillProblemEntity entity = fillProblemService.getById(id);

        return Result.ok(FillProblemConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody FillProblemVO vo){
        fillProblemService.save(vo);

        return Result.ok();
    }

    @GetMapping("updateStatus/{problemId}")
    @Operation(summary = "修改状态")
    public Result<String> updateStatus(@PathVariable("problemId")  Long problemId){
        fillProblemService.updateStatus(problemId);
        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid FillProblemVO vo){
        fillProblemService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        fillProblemService.delete(idList);

        return Result.ok();
    }

    @GetMapping("usedNum")
    @Operation(summary = "修改引用次数")
    public Result<String> updateUsedNum(@RequestParam Long id ){
        fillProblemService.updateUsedNum(id);

        return Result.ok();
    }

    @GetMapping("submitTimes")
    @Operation(summary = "修改提交和正确次数")
    public Result<String> updateSubmitTimes(@RequestParam Long id , @RequestParam Boolean isTrue ){
        fillProblemService.updateSubmitTimes(id,isTrue);

        return Result.ok( );
    }

    @GetMapping("fillProblemInfo/{problemId}")
    @Operation(summary = "获取填空题详情")
    public Result<FillProblemVO> selectFillProblemInfo(@PathVariable("problemId")  Long problemId){
        return Result.ok(fillProblemService.selectFillProblemInfo(problemId));
    }


}