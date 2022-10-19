package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.ExamAttendLogConvert;
import net.edu.module.entity.ExamAttendLogEntity;
import net.edu.module.query.ExamAttendLogQuery;
import net.edu.module.service.ExamAttendLogService;
import net.edu.module.vo.ExamAttendLogVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("exam/attend")
@Tag(name = "课程表")
@AllArgsConstructor
public class ExamAttendLogController {

    private final ExamAttendLogService examAttendLogService;

    @GetMapping("page")
    @Operation(summary = "分页")

    public Result<PageResult<ExamAttendLogVO>> page(@Valid ExamAttendLogQuery query){
        PageResult<ExamAttendLogVO> page = examAttendLogService.page(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<ExamAttendLogVO> get(@PathVariable("id") Long id){
        ExamAttendLogEntity entity = examAttendLogService.getById(id);

        return Result.ok(ExamAttendLogConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody ExamAttendLogVO vo){
        examAttendLogService.save(vo);

        return Result.ok();
    }


    @GetMapping("user/{examId}")
    @Operation(summary = "教师点名获取课次中的学生")
    public Result<ExamAttendLogVO> studentsList(@PathVariable("examId") Long examId) {
        return Result.ok(examAttendLogService.getUserExamAttend(examId));
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        examAttendLogService.delete(idList);

        return Result.ok();
    }





}
