package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.ArchiveAssessConvert;
import net.edu.module.entity.ArchiveAssessEntity;
import net.edu.module.service.ArchiveAssessService;
import net.edu.module.query.ArchiveAssessQuery;
import net.edu.module.vo.ArchiveAssessVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* 考核点
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-26
*/
@RestController
@RequestMapping("assess")
@Tag(name="考核点")
@AllArgsConstructor
public class ArchiveAssessController {
    private final ArchiveAssessService archiveAssessService;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<ArchiveAssessVO>> page(@Valid ArchiveAssessQuery query){
        PageResult<ArchiveAssessVO> page = archiveAssessService.page(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<ArchiveAssessVO> get(@PathVariable("id") Long id){
        ArchiveAssessEntity entity = archiveAssessService.getById(id);

        return Result.ok(ArchiveAssessConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody ArchiveAssessVO vo){
        archiveAssessService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid ArchiveAssessVO vo){
        archiveAssessService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        archiveAssessService.delete(idList);

        return Result.ok();
    }
}
