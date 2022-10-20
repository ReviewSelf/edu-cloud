package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.GraduateRequireConvert;
import net.edu.module.entity.GraduateRequireEntity;
import net.edu.module.query.GraduateRequireQuery;
import net.edu.module.service.GraduateRequireService;
import net.edu.module.vo.GraduateRequireVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* 毕业要求
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-20
*/
@RestController
@RequestMapping("graduateRequire")
@Tag(name="毕业要求")
@AllArgsConstructor
public class GraduateRequireController {
    private final GraduateRequireService graduateRequireService;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<GraduateRequireVO>> page(@Valid GraduateRequireQuery query){
        PageResult<GraduateRequireVO> page = graduateRequireService.page(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<GraduateRequireVO> get(@PathVariable("id") Long id){
        GraduateRequireEntity entity = graduateRequireService.getById(id);
        return Result.ok(GraduateRequireConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody GraduateRequireVO vo){
        graduateRequireService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid GraduateRequireVO vo){
        graduateRequireService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        graduateRequireService.delete(idList);

        return Result.ok();
    }
}
