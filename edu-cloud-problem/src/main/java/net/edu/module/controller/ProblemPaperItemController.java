package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.ProblemPaperItemConvert;
import net.edu.module.entity.ProblemPaperItemEntity;
import net.edu.module.service.ProblemPaperItemService;
import net.edu.module.query.ProblemPaperItemQuery;
import net.edu.module.vo.ProblemPaperItemVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* 问题卷关联问题表
*
* @author 樊磊 
* @since 1.0.0 2022-09-06
*/
@RestController
@RequestMapping("paperItem")
@Tag(name="问题卷关联问题表")
@AllArgsConstructor
public class ProblemPaperItemController {
    private final ProblemPaperItemService problemPaperItemService;



    @GetMapping("{paperId}")
    @Operation(summary = "信息")
    public Result<List<ProblemPaperItemEntity>> get(@PathVariable("paperId") Integer paperId){
        List<ProblemPaperItemEntity> entity = problemPaperItemService.get(paperId);

        return Result.ok(entity);
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody List<ProblemPaperItemEntity> list){
        problemPaperItemService.insert(list);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestParam(value = "paperId") Integer paperId){
        problemPaperItemService.delete(paperId);
        return Result.ok();
    }

}