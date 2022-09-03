package net.edu.module.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.entity.CodeProblemEntity;
import net.edu.module.query.ProblemQuery;
import net.edu.module.service.CodeProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * @Author: 马佳浩
 * @Date: 2022/9/3 18:50
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping("/code")
@Tag(name="问题列表接口")
@AllArgsConstructor
public class CodeProblemController {

    @Autowired
    private CodeProblemService codeProblemServiceImpl;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<CodeProblemEntity>> page(@Valid ProblemQuery query){
        PageResult<CodeProblemEntity> page = codeProblemServiceImpl.getProblemPage(query);

        return Result.ok(page);
    }


}
