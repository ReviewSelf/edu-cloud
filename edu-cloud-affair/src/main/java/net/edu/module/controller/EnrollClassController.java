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
@RequestMapping("enrollClass")
@Tag(name="班级发布")
@AllArgsConstructor
public class EnrollClassController {
    private final EnrollClassService enrollClassService;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<EnrollClassVO>> page(@Valid EnrollClassQuery query){
        PageResult<EnrollClassVO> page = enrollClassService.page(query);
        return Result.ok(page);
    }


    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<EnrollClassVO> get(@PathVariable("id") Long id){
        EnrollClassEntity entity = enrollClassService.getById(id);
        System.out.println(entity);
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

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        enrollClassService.delete(idList);

        return Result.ok();
    }

    @PutMapping("status/{id}")
    @Operation(summary = "更新状态")
    public Result<String> updateStatus(@PathVariable("id") Long id){
        enrollClassService.updateStatus(id);
        return Result.ok();
    }

    /**
     * 获取所有发布的班级
     * @return
     */
    @GetMapping("publish")
    @Operation(summary = "分页")
    public Result<List<EnrollClassVO>> selectPublish(){
        return Result.ok(enrollClassService.selectPublish());
    }

}
