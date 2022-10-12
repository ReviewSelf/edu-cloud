package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.ExamIPConvert;
import net.edu.module.entity.ExamIPEntity;
import net.edu.module.query.ExamIPQuery;
import net.edu.module.service.ExamIPService;
import net.edu.module.vo.ExamIPVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author: 马佳浩
 * @Date: 2022/10/12 9:56
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping("/exam/ip")
@Tag(name="examIP")
@AllArgsConstructor
public class ExamIPController {
    private final ExamIPService examIpService;

    @GetMapping("list")
    @Operation(summary = "ip列表")
    public Result<List<ExamIPVO>> list(@Valid ExamIPQuery query){
        List<ExamIPVO> list = examIpService.list(query);

        return Result.ok(list);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<ExamIPVO> get(@PathVariable("id") Long id){
        ExamIPEntity entity = examIpService.getById(id);

        return Result.ok(ExamIPConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody ExamIPVO vo){
        examIpService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid ExamIPVO vo){
        examIpService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        examIpService.delete(idList);

        return Result.ok();
    }
}
