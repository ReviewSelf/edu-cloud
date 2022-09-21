package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.module.query.LessonAttendLogQuery;
import net.edu.module.service.LessonAttendLogService;
import net.edu.module.vo.LessonAttendLogVO;
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
    @Operation(summary = "教师点名获取课次中的学生")
    public Result<List<LessonAttendLogVO>> studentsList(@Valid LessonAttendLogQuery query) {
        return Result.ok(lessonAttendLogService.list(query));
    }

    @PutMapping("students/update")
    @Operation(summary = "教师确认课堂学生名单")
    public Result<String> updateStudents(@RequestBody LessonAttendLogVO vo) {
        lessonAttendLogService.updateStudents(vo);
        return Result.ok();
    }
}
