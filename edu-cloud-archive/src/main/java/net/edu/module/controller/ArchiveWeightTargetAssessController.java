package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.ArchiveWeightTargetAssessConvert;
import net.edu.module.entity.ArchiveWeightTargetAssessEntity;
import net.edu.module.service.ArchiveWeightTargetAssessService;
import net.edu.module.query.ArchiveWeightTargetAssessQuery;
import net.edu.module.vo.ArchiveWeightTargetAssessVO;
import net.edu.module.vo.ArchiveWeightTargetKnowledgeVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* 考核点权重
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-29
*/
@RestController
@RequestMapping("weightAssess")
@Tag(name="考核点权重")
@AllArgsConstructor
public class ArchiveWeightTargetAssessController {
    private final ArchiveWeightTargetAssessService archiveWeightTargetAssessService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @PreAuthorize("hasAuthority('archiveweighttargetassess:page')")
    public Result<PageResult<ArchiveWeightTargetAssessVO>> page(@Valid ArchiveWeightTargetAssessQuery query){
        PageResult<ArchiveWeightTargetAssessVO> page = archiveWeightTargetAssessService.page(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @PreAuthorize("hasAuthority('archiveweighttargetassess:info')")
    public Result<ArchiveWeightTargetAssessVO> get(@PathVariable("id") Long id){
        ArchiveWeightTargetAssessEntity entity = archiveWeightTargetAssessService.getById(id);

        return Result.ok(ArchiveWeightTargetAssessConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    @PreAuthorize("hasAuthority('archiveweighttargetassess:save')")
    public Result<String> save(@RequestBody ArchiveWeightTargetAssessVO vo){
        archiveWeightTargetAssessService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    @PreAuthorize("hasAuthority('archiveweighttargetassess:update')")
    public Result<String> update(@RequestBody @Valid ArchiveWeightTargetAssessVO vo){
        archiveWeightTargetAssessService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @PreAuthorize("hasAuthority('archiveweighttargetassess:delete')")
    public Result<String> delete(@RequestBody List<Long> idList){
        archiveWeightTargetAssessService.delete(idList);

        return Result.ok();
    }

    @GetMapping("course")
    @Operation(summary = "根据课程id获取权重")
    public Result<List<ArchiveWeightTargetAssessVO>> selectKnowledgeByTargetId(Long courseId){
        return Result.ok(archiveWeightTargetAssessService.selectAssessByCourseId(courseId));
    }

    @PostMapping("target")
    @Operation(summary = "插入指标点id以及对应课程id和权重")
    public Result<String> insertKnowledgeWeight(@RequestBody List<ArchiveWeightTargetAssessVO> vo){
        System.out.println("123"+vo);
        archiveWeightTargetAssessService.insertAssessWeight(vo);
        return Result.ok();
    }
}
