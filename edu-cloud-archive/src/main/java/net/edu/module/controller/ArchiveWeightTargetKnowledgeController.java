package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.ArchiveWeightTargetKnowledgeConvert;
import net.edu.module.entity.ArchiveWeightTargetKnowledgeEntity;
import net.edu.module.service.ArchiveWeightTargetKnowledgeService;
import net.edu.module.query.ArchiveWeightTargetKnowledgeQuery;
import net.edu.module.vo.ArchiveWeightTargetKnowledgeVO;
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
public class ArchiveWeightTargetKnowledgeController {
    private final ArchiveWeightTargetKnowledgeService archiveWeightTargetKnowledgeService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @PreAuthorize("hasAuthority('archiveweighttargetknowledge:page')")
    public Result<PageResult<ArchiveWeightTargetKnowledgeVO>> page(@Valid ArchiveWeightTargetKnowledgeQuery query){
        PageResult<ArchiveWeightTargetKnowledgeVO> page = archiveWeightTargetKnowledgeService.page(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @PreAuthorize("hasAuthority('archiveweighttargetknowledge:info')")
    public Result<ArchiveWeightTargetKnowledgeVO> get(@PathVariable("id") Long id){
        ArchiveWeightTargetKnowledgeEntity entity = archiveWeightTargetKnowledgeService.getById(id);

        return Result.ok(ArchiveWeightTargetKnowledgeConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    @PreAuthorize("hasAuthority('archiveweighttargetknowledge:save')")
    public Result<String> save(@RequestBody ArchiveWeightTargetKnowledgeVO vo){
        archiveWeightTargetKnowledgeService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    @PreAuthorize("hasAuthority('archiveweighttargetknowledge:update')")
    public Result<String> update(@RequestBody @Valid ArchiveWeightTargetKnowledgeVO vo){
        archiveWeightTargetKnowledgeService.update(vo);

        return Result.ok();
    }

    @DeleteMapping("")
    @Operation(summary = "删除")
    public Result<String> delete(@RequestParam("targetId")Long targetId,@RequestParam("courseId")Long courseId){
        archiveWeightTargetKnowledgeService.delete(targetId,courseId);

        return Result.ok();
    }

    @GetMapping("target")
    @Operation(summary = "根据指标点id获取权重")
    public Result<List<ArchiveWeightTargetKnowledgeVO>> selectKnowledgeByTargetId(Long targetId){
        return Result.ok(archiveWeightTargetKnowledgeService.selectKnowledgeByTargetId(targetId));
    }

    @PostMapping("target")
    @Operation(summary = "插入指标点id以及对应课程id和权重")
    public Result<String> insertKnowledgeWeight(@RequestBody List<ArchiveWeightTargetKnowledgeVO> vo){
        archiveWeightTargetKnowledgeService.insertKnowledgeWeight(vo);
        return Result.ok();
    }
}