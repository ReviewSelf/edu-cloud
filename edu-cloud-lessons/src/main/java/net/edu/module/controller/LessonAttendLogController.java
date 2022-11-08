package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.module.query.LessonAttendLogQuery;
import net.edu.module.service.LessonAttendLogService;
import net.edu.module.vo.LessonAttendLogVO;
import net.edu.module.vo.LessonStudentVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("attend")
@Tag(name = "课程表")
@AllArgsConstructor
public class LessonAttendLogController {

    private final LessonAttendLogService lessonAttendLogService;

    @GetMapping("students/list")
    @Operation(summary = "教师点名,获取课次中的学生")
    public Result<List<LessonAttendLogVO>> studentsList(@Valid LessonAttendLogQuery query) {
        return Result.ok(lessonAttendLogService.list(query));
    }

    @PutMapping("students/update")
    @Operation(summary = "教师确认课堂学生名单")
    public Result<String> updateStudents(@RequestBody LessonAttendLogVO vo) {
        lessonAttendLogService.updateStudents(vo);
        return Result.ok();
    }

    @PostMapping("lesson")
    @Operation(summary = "根据学生id批量插入课程id")
    public Result insertLessonList(@RequestBody LessonStudentVO vo){
        lessonAttendLogService.insertLessonList(vo);
        return Result.ok();
    }

    @DeleteMapping("lesson")
    @Operation(summary = "根据学生id批量删除课程id")
    public Result deleteLessonList(@RequestBody LessonStudentVO vo){
        lessonAttendLogService.deleteLessonList(vo);
        return Result.ok();
    }
}
