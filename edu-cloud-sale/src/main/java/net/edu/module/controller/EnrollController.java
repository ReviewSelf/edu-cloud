package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.EnrollConvert;
import net.edu.module.entity.EnrollEntity;
import net.edu.module.query.EnrollQuery;
import net.edu.module.service.EnrollService;
import net.edu.module.vo.EnrollUserVO;
import net.edu.module.vo.EnrollVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * @author weng
 * @date 2023/1/13 - 13:19
 **/
@RestController
@RequestMapping("enroll")
public class EnrollController {

    @Autowired
    private EnrollService enrollService;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<EnrollVO>> page(@Valid EnrollQuery query){
        PageResult<EnrollVO> page = enrollService.page(query);
        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<EnrollVO> get(@PathVariable("id") Long id){
        EnrollEntity entity = enrollService.getById(id);

        return Result.ok(EnrollConvert.INSTANCE.convert(entity));
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid EnrollVO vo){
        enrollService.update(vo);

        return Result.ok();
    }

    @PutMapping("/{id}")
    @Operation(summary = "放弃学员")
    public Result<String> abandon(@PathVariable("id") Long id){
        enrollService.abandon(id);

        return Result.ok();
    }
    @PostMapping("import")
    public Result<String> studentFromExcel(@RequestParam("file") MultipartFile file) {
        System.out.println("go");
        enrollService.studentFromExcel(file);
        return Result.ok();
    }

}