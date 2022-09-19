package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.TeachClassUserConvert;
import net.edu.module.entity.TeachClassUserEntity;
import net.edu.module.query.TeachClassUserQuery;
import net.edu.module.service.TeachClassUserService;
import net.edu.module.vo.TeachClassUserVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
* 班级用户表
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-09-16
*/
@RestController
@RequestMapping("classUser")
@Tag(name="班级用户表")
@AllArgsConstructor
public class TeachClassUserController {

    private  final TeachClassUserService teachClassUserService;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<TeachClassUserVO>> page(@Valid TeachClassUserQuery query){
        PageResult<TeachClassUserVO> page = teachClassUserService.page(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<TeachClassUserVO> get(@PathVariable("id") Long id){
        TeachClassUserEntity entity = teachClassUserService.getById(id);

        return Result.ok(TeachClassUserConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody TeachClassUserVO vo){

        teachClassUserService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid TeachClassUserVO vo){
        teachClassUserService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        teachClassUserService.delete(idList);

        return Result.ok();
    }

    @PutMapping("/quit")
    @Operation(summary = "退班")
    public Result<String> quitClass(@RequestBody @Valid TeachClassUserVO vo){
        teachClassUserService.quitClass(vo.getClassId(),vo.getUserId(), vo.getQuitTime());
        return Result.ok();
    }
}