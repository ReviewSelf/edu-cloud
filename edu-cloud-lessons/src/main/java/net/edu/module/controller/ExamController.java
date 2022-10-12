package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.framework.security.user.SecurityUser;
import net.edu.module.convert.ExamConvert;
import net.edu.module.entity.ExamEntity;
import net.edu.module.service.ExamService;
import net.edu.module.query.ExamQuery;
import net.edu.module.vo.ExamVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* 考试
*
* @author 小樊 babamu@126.com
* @since 1.0.0 2022-10-09
*/
@RestController
@RequestMapping("/exam")
@Tag(name="考试")
@AllArgsConstructor
public class ExamController {
    private final ExamService examService;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<ExamVO>> page(@Valid ExamQuery query){
        PageResult<ExamVO> page = examService.page(query);
        System.out.println(query);
        return Result.ok(page);
    }

    @GetMapping("studentPage")
    @Operation(summary = "学生端分页")
    public Result<PageResult<ExamVO>> studentPage(@Valid ExamQuery query){
        query.setUserId(SecurityUser.getUserId());
        PageResult<ExamVO> page = examService.studentPage(query);
        System.out.println(SecurityUser.getUserId());
        return Result.ok(page);
    }

    @GetMapping("Examing")
    @Operation(summary = "正在进行的考试")
    public Result<List<ExamVO>> list(){
        List<ExamVO> list = examService.getExamingList(SecurityUser.getUserId());
        return Result.ok(list);
    }


    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<ExamVO> get(@PathVariable("id") Long id){
        ExamEntity entity = examService.getById(id);

        return Result.ok(ExamConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody ExamVO vo){
        vo.setTeacherId(SecurityUser.getUserId());
        examService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid ExamVO vo){
        examService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        examService.delete(idList);

        return Result.ok();
    }
}