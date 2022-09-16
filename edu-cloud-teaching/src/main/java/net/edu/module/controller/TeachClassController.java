package net.edu.module.controller;




import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.TeachClassConvert;
import net.edu.module.entity.TeachClassEntity;
import net.edu.module.query.TeachClassQuery;
import net.edu.module.service.TeachClassService;
import net.edu.module.vo.TeachClassVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 班级表
 *
 * @author wengruichen babamu@126.com
 * @since 1.0.0 2022-09-09
 */
@RestController
@RequestMapping("teach/class")
@Tag(name="班级表")
public class TeachClassController {
    @Autowired
    TeachClassService teachClassService;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<TeachClassVO>> page(@Valid TeachClassQuery query){
        System.out.println(query);
        PageResult<TeachClassVO> page = teachClassService.page(query);

        return Result.ok(page);
    }


    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<TeachClassVO> get(@PathVariable("id") Long id){
        TeachClassEntity entity = teachClassService.getById(id);

        return Result.ok(TeachClassConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody TeachClassVO vo){
        teachClassService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid TeachClassVO vo){
        teachClassService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        teachClassService.delete(idList);

        return Result.ok();
    }
}
