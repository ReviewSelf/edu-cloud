package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.EnrollClassConvert;
import net.edu.module.entity.EnrollClassEntity;
import net.edu.module.query.EnrollClassQuery;
import net.edu.module.service.EnrollClassService;
import net.edu.module.vo.EnrollClassVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* 班级发布
*
* @author 翁瑞辰 
* @since  2022-09-06
*/
@RestController
@RequestMapping("enrollclass")
@Tag(name="班级发布")
@AllArgsConstructor
public class EnrollClassController {
    private final EnrollClassService enrollClassService;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Object getPage(@RequestParam("pageIndex") Integer pageIndex,
                          @RequestParam("pageSize") Integer pageSize){
        return Result.ok(enrollClassService.getDailyReports(pageIndex,pageSize));
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<EnrollClassVO> get(@PathVariable("id") Long id){
        EnrollClassEntity entity = enrollClassService.getById(id);

        return Result.ok(EnrollClassConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody EnrollClassVO vo){
        enrollClassService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid EnrollClassVO vo){
        enrollClassService.update(vo);

        return Result.ok();
    }

    @PutMapping("{id}")
    @Operation(summary = "假删除")
    public Result<String> delete(@PathVariable("id") Long id){
        enrollClassService.deleteEnrollClass(id);
        return Result.ok();
    }
}