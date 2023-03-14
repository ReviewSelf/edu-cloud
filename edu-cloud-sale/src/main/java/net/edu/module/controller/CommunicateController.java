package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.CommunicateConvert;
import net.edu.module.entity.CommunicateEntity;
import net.edu.module.query.CommunicateQuery;
import net.edu.module.service.CommunicateService;
import net.edu.module.vo.CommunicateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author weng
 * @date 2023/1/13 - 13:19
 **/
@RestController
@RequestMapping("communicate")
public class CommunicateController {

    @Autowired
    private CommunicateService communicateService;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<CommunicateVO>> page(@Valid CommunicateQuery query){
        PageResult<CommunicateVO> page = communicateService.page(query);
        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "详情")
    public Result<CommunicateVO> get(@PathVariable("id") Long id){
        return Result.ok(communicateService.getCommunicateInfo(id));
    }


    @PostMapping
    @Operation(summary = "新增沟通记录")
    public Result<String> insert(@RequestBody CommunicateEntity entity){
        communicateService.save(entity);
        return Result.ok();
    }
}