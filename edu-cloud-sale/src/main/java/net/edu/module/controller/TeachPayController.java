package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.query.TeachPayQuery;
import net.edu.module.service.TeachPayService;
import net.edu.module.vo.TeachPayVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

/**
* 流水账管理
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-09-16
*/
@RestController
@RequestMapping("pay")
@Tag(name="流水账管理")
@AllArgsConstructor
public class TeachPayController {
    private final TeachPayService teachPayService;

    @GetMapping("page")
    @Operation(summary = "分页")

    public Result<PageResult<TeachPayVO>> page(@Valid TeachPayQuery query){
        PageResult<TeachPayVO> page = teachPayService.page(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "学生缴费详情")
    public Result<TeachPayVO> get(@PathVariable("id") Long id){
        TeachPayVO vo = teachPayService.getPaymentDetail(id);

        return Result.ok(vo);
    }

    @PostMapping
    @Operation(summary = "缴费成功")
    //此时需要添加缴费记录，修改学生表记录
    public Result<String> save(@RequestBody TeachPayVO vo){
        teachPayService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")

    public Result<String> update(@RequestBody @Valid TeachPayVO vo){
        teachPayService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")

    public Result<String> delete(@RequestBody List<Long> idList){
        teachPayService.delete(idList);

        return Result.ok();
    }

    @PutMapping("return")
    @Operation(summary = "退费")
    public Result<String> returnBack(@RequestBody @Valid TeachPayVO vo){
        teachPayService.returnBack(vo);

        return Result.ok();
    }

    @GetMapping("month/amount")
    @Operation(summary = "每月统计")
    public Result<List<HashMap<String,String>>> monthAmount(){

        List<HashMap<String, String>> statistics = teachPayService.statisticsAmount();

        return Result.ok(statistics);
    }

    @GetMapping("month/people")
    @Operation(summary = "每月统计")
    public Result<List<HashMap<String,String>>> monthPeople(){

        List<HashMap<String, String>> statistics = teachPayService.statisticsPeople();

        return Result.ok(statistics);
    }
}