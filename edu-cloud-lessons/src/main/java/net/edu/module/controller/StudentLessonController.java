package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.module.query.LessonQuery;
import net.edu.module.service.StudentLessonService;
import net.edu.module.vo.LessonVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/15 9:33
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping("stu")
@AllArgsConstructor
public class StudentLessonController {

    private final StudentLessonService studentLessonService;

    @GetMapping("{lessonId}")
    @Operation(summary = "课程列表")
    public Result<String> attendLesson( @PathVariable Long lessonId){
        return studentLessonService.attendLesson(lessonId);
    }


}
