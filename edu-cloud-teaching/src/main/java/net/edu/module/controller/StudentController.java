package net.edu.module.controller;


import io.swagger.v3.oas.annotations.OpenAPI30;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.edu.framework.common.utils.Result;
import net.edu.module.service.StudentService;
import net.edu.module.vo.HomeWorkVO;
import net.edu.module.vo.OrgVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    /**
     * 根据unionId获取学生Id
     */

    @GetMapping("/getStudentId")
    public String getStudentId(String unionId){
        return  studentService.getStudentId(unionId);
    }

    @GetMapping("/orgList")
    @Operation(summary = "机构列表")
    public Result<List<OrgVo>> orgList() {
        List<OrgVo> orglist = studentService.getOrgList();

        System.out.println("orglist"+orglist);
        return Result.ok(orglist);
    }


}
