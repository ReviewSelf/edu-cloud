package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.ArchiveTargetConvert;
import net.edu.module.entity.ArchiveTargetEntity;
import net.edu.module.service.ArchiveTargetService;
import net.edu.module.query.ArchiveTargetQuery;
import net.edu.module.vo.ArchiveTargetVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* target
*
* @author qxd babamu@126.com
* @since 1.0.0 2022-10-24
*/
@RestController
@RequestMapping("target")
@Tag(name="target")
@AllArgsConstructor
public class ArchiveTargetController {
    private final ArchiveTargetService archiveTargetService;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<ArchiveTargetVO>> page(@Valid ArchiveTargetQuery query){
        PageResult<ArchiveTargetVO> page = archiveTargetService.page(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<ArchiveTargetVO> get(@PathVariable("id") Long id){
        ArchiveTargetEntity entity = archiveTargetService.getById(id);

        return Result.ok(ArchiveTargetConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody ArchiveTargetVO vo){
        archiveTargetService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid ArchiveTargetVO vo){
        archiveTargetService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        archiveTargetService.delete(idList);

        return Result.ok();
    }
}
