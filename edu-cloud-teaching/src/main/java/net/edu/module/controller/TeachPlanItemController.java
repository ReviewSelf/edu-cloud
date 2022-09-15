package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.TeachPlanItemConvert;
import net.edu.module.entity.TeachPlanItemEntity;
import net.edu.module.query.TeachPlanItemQuery;
import net.edu.module.service.TeachPlanItemService;
import net.edu.module.vo.TeachPlanItemPaperVO;
import net.edu.module.vo.TeachPlanItemResourceVO;
import net.edu.module.vo.TeachPlanItemVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* 教学日历表
*
* @author sqw 
* @since 1.0.0 2022-09-12
*/
@RestController
@RequestMapping("planItem")
@Tag(name="教学日历表")
@AllArgsConstructor
public class TeachPlanItemController {
    private final TeachPlanItemService teachPlanItemService;

    @GetMapping("page/{id}")
    @Operation(summary = "分页")
    public Result<List<TeachPlanItemVO>> page(@PathVariable("id")  Long id){
        List<TeachPlanItemVO> page = teachPlanItemService.page(id);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<TeachPlanItemVO> get(@PathVariable("id") Long id){
        TeachPlanItemEntity entity = teachPlanItemService.getById(id);

        return Result.ok(TeachPlanItemConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody TeachPlanItemVO vo){
        teachPlanItemService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid TeachPlanItemVO vo){
        teachPlanItemService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        teachPlanItemService.delete(idList);

        return Result.ok();
    }

    @GetMapping("paper/{id}")
    @Operation(summary = "获取教学试卷")
    public Result<List<TeachPlanItemPaperVO>> getItemPaper(@PathVariable("id") Long id){
        List<TeachPlanItemPaperVO> list = teachPlanItemService.getItemPaper(id);
        return Result.ok(list);
    }

    @PostMapping("paper")
    @Operation(summary = "更新教学试卷")
    public Result<String> updateItemPaper(@RequestBody List<TeachPlanItemPaperVO> list){
         if(list.size()>0){
             teachPlanItemService.updateItemPaper(list);
             return Result.ok();
         }
         return Result.error("试卷不许为空！");
    }

    @GetMapping("resource/{id}")
    @Operation(summary = "获取日历资源")
    public Result<List<TeachPlanItemResourceVO>> getItemResource(@PathVariable("id") Long id){
        List<TeachPlanItemResourceVO> list = teachPlanItemService.getItemResource(id);
        return Result.ok(list);
    }

    @PostMapping("resource")
    @Operation(summary = "保存日历资源")
    public Result<String> saveItemResource(@RequestBody TeachPlanItemResourceVO vo){
            teachPlanItemService.saveItemResource(vo);
            return Result.ok();
    }


    @DeleteMapping("resource")
    @Operation(summary = "删除日历资源")
    public Result<String> deleteItemResource(@RequestBody List<Long> idList){
        teachPlanItemService.deleteItemResource(idList);

        return Result.ok();
    }

}