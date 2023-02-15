package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.framework.security.user.SecurityUser;
import net.edu.module.convert.TeachEvaluateConvert;
import net.edu.module.entity.TeachEvaluateEntity;
import net.edu.module.service.TeachEvaluateService;
import net.edu.module.query.TeachEvaluateQuery;
import net.edu.module.vo.TeachEvaluateVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* 教学评价
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-08
*/
@RestController
@RequestMapping("evaluate")
@Tag(name="教学评价")
@AllArgsConstructor
public class TeachEvaluateController {
    private final TeachEvaluateService teachEvaluateService;

    @GetMapping("page")
    @Operation(summary = "查看评价")
    public Result<PageResult<TeachEvaluateVO>> page(@Valid TeachEvaluateQuery query){
        PageResult<TeachEvaluateVO> page = teachEvaluateService.page(query);

        return Result.ok(page);
    }

}