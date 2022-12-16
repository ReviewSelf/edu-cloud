package net.edu.module.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.ArchiveSignConvert;
import net.edu.module.entity.ArchiveSignEntity;
import net.edu.module.query.ArchiveSignQuery;
import net.edu.module.service.ArchiveExamService;
import net.edu.module.service.ArchiveSignService;
import net.edu.module.vo.ArchiveSignVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("sign")
@Tag(name="记分册签到表")
@AllArgsConstructor
public class ArchiveSignController {

    @Autowired
    private ArchiveSignService archiveSignService;

    @PostMapping("/import")
    public Result<String> scoreImportExcel(@RequestParam("bookId") String bookId,@RequestParam("file") MultipartFile file) {

        archiveSignService.signImportExcel(file, Long.valueOf(bookId));
        return Result.ok();
    }

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<ArchiveSignVO>> page(@Valid ArchiveSignQuery query){

        PageResult<ArchiveSignVO> page = archiveSignService.page(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<ArchiveSignVO> get(@PathVariable("id") Long id){
        ArchiveSignEntity entity = archiveSignService.getById(id);

        return Result.ok(ArchiveSignConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody ArchiveSignVO vo){
        archiveSignService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid ArchiveSignVO vo){
        archiveSignService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        archiveSignService.delete(idList);

        return Result.ok();
    }
}
