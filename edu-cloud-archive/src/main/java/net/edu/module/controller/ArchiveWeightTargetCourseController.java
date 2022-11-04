package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.ArchiveWeightTargetCourseConvert;
import net.edu.module.entity.ArchiveWeightTargetCourseEntity;
import net.edu.module.service.ArchiveWeightTargetCourseService;
import net.edu.module.query.ArchiveWeightTargetCourseQuery;
import net.edu.module.vo.ArchiveWeightTargetCourseVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* 一级知识点权重
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-29
*/
@RestController
@RequestMapping("weightKnowledge")
@Tag(name="一级知识点权重")
@AllArgsConstructor
public class ArchiveWeightTargetCourseController {
    private final ArchiveWeightTargetCourseService archiveWeightTargetCourseService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @PreAuthorize("hasAuthority('archiveweighttargetknowledge:page')")
    public Result<PageResult<ArchiveWeightTargetCourseVO>> page(@Valid ArchiveWeightTargetCourseQuery query){
        PageResult<ArchiveWeightTargetCourseVO> page = archiveWeightTargetCourseService.page(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @PreAuthorize("hasAuthority('archiveweighttargetknowledge:info')")
    public Result<ArchiveWeightTargetCourseVO> get(@PathVariable("id") Long id){
        ArchiveWeightTargetCourseEntity entity = archiveWeightTargetCourseService.getById(id);

        return Result.ok(ArchiveWeightTargetCourseConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    @PreAuthorize("hasAuthority('archiveweighttargetknowledge:save')")
    public Result<String> save(@RequestBody ArchiveWeightTargetCourseVO vo){
        archiveWeightTargetCourseService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    @PreAuthorize("hasAuthority('archiveweighttargetknowledge:update')")
    public Result<String> update(@RequestBody @Valid ArchiveWeightTargetCourseVO vo){
        archiveWeightTargetCourseService.update(vo);

        return Result.ok();
    }

    @DeleteMapping("")
    @Operation(summary = "删除")
    public Result<String> delete(@RequestParam("targetId")Long targetId,@RequestParam("courseId")Long courseId){
        archiveWeightTargetCourseService.delete(targetId,courseId);

        return Result.ok();
    }

    @GetMapping("target")
    @Operation(summary = "根据指标点id获取权重")
    public Result<List<ArchiveWeightTargetCourseVO>> selectKnowledgeByTargetId(@RequestParam("targetId") Long targetId){
        return Result.ok(archiveWeightTargetCourseService.selectKnowledgeByTargetId(targetId));
    }

    @PostMapping("target")
    @Operation(summary = "插入指标点id以及对应课程id和权重")
    public Result<String> insertKnowledgeWeight(@RequestBody List<ArchiveWeightTargetCourseVO> vo){
        archiveWeightTargetCourseService.insertKnowledgeWeight(vo);
        return Result.ok();
    }
}
