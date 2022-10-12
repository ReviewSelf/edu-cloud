package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.module.query.ExamAttendLogQuery;
import net.edu.module.service.ExamAttendLogService;
import net.edu.module.vo.ExamAttendLogVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("attend")
@Tag(name = "课程表")
@AllArgsConstructor
public class ExamAttendLogController {

    private final ExamAttendLogService examAttendLogService;

    @GetMapping("students/list")
    @Operation(summary = "教师点名获取课次中的学生")
    public Result<List<ExamAttendLogVO>> studentsList(@Valid ExamAttendLogQuery query) {
        return Result.ok(examAttendLogService.list(query));
    }

    @PutMapping("students/update")
    @Operation(summary = "教师确认课堂学生名单")
    public Result<String> updateStudents(@RequestBody ExamAttendLogVO vo) {
        examAttendLogService.updateStudents(vo);
        return Result.ok();
    }

    @PostMapping("lesson")
    @Operation(summary = "根据学生id批量插入课程id")
    public Result insertExamList(@RequestBody ExamAttendLogVO vo){

        return Result.ok();
    }

    @DeleteMapping("lesson")
    @Operation(summary = "根据学生id批量删除课程id")
    public Result deleteExamList(@RequestBody ExamAttendLogVO vo){
        System.out.println(vo);

        return Result.ok();
    }
}
