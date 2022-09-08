package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.EnrollUserConvert;
import net.edu.module.entity.EnrollUserEntity;
import net.edu.module.query.EnrollUserQuery;


import net.edu.module.service.EnrollUserService;
import net.edu.module.vo.EnrollUserVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* XinXiHeShi
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-09-05
*/
@RestController
@RequestMapping("enroll/user")
@Tag(name="XinXiHeShi")
@AllArgsConstructor
public class EnrollUserController {
    private final EnrollUserService enrollUserService;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<EnrollUserVO>> page(@Valid EnrollUserQuery query){
        PageResult<EnrollUserVO> page = enrollUserService.page(query);
        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<EnrollUserVO> get(@PathVariable("id") Long id){
        EnrollUserEntity entity = enrollUserService.getById(id);

        return Result.ok(EnrollUserConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody EnrollUserVO vo){
        enrollUserService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid EnrollUserVO vo){
        enrollUserService.update(vo);

        return Result.ok();
    }


//    @DeleteMapping("{id}")
//    @Operation(summary = "删除")
//    public Result<String> delete(@PathVariable("id") Long id){
//        enrollUserService.deleteEnrollUser(id);
//        return Result.ok();
//    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        enrollUserService.delete(idList);

        return Result.ok();
    }

}
