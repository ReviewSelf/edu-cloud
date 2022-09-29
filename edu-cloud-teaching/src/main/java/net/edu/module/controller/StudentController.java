package net.edu.module.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import net.edu.framework.common.utils.Result;
import net.edu.module.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("studentEx")
@Tag(name="学生表")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/import")
    public Result<String> studentFromExcel(@RequestParam("file") MultipartFile file) {
        studentService.studentFromExcel(file);
        return Result.ok();
    }
}
