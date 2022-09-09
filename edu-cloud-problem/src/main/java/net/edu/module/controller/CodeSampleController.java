package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.api.EduFileApi;
import net.edu.module.convert.CodeSampleConvert;
import net.edu.module.entity.CodeSampleEntity;
import net.edu.module.query.CodeSampleQuery;
import net.edu.module.service.CodeSampleService;
import net.edu.module.vo.CodeSampleVO;
import net.edu.module.vo.SampleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
* 测试样例表
*
* @author sqw 
* @since 1.0.0 2022-09-07
*/
@RestController
@RequestMapping("sample")
@Tag(name="测试样例表")
@AllArgsConstructor
public class CodeSampleController {
    private final CodeSampleService codeSampleService;

    @Autowired
    private EduFileApi eduFileApi;


    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<CodeSampleVO>> page(@Valid CodeSampleQuery query){
        PageResult<CodeSampleVO> page = codeSampleService.page(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<CodeSampleVO> get(@PathVariable("id") Long id){
        CodeSampleEntity entity = codeSampleService.getById(id);

        return Result.ok(CodeSampleConvert.INSTANCE.convert(entity));
    }

    @PostMapping("file")
    @Operation(summary = "保存样例文件")
    public Result<String> saveSample(@RequestParam("input") MultipartFile[] inFiles,@RequestParam("output") MultipartFile[] outFiles,@RequestParam("problemId") Long problemId){
        List<SampleVO> sampleVOS=eduFileApi.uploadBatch(inFiles,outFiles, problemId);
        codeSampleService.saveSample(sampleVOS,problemId);
        return Result.ok();
    }


    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid CodeSampleVO vo){
        codeSampleService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        codeSampleService.delete(idList);

        return Result.ok();
    }
}