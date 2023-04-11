package net.edu.module.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.query.PublicityQuery;
import net.edu.module.service.PublicityService;
import net.edu.module.vo.ReferenceVO;
import net.edu.module.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("publicity")
@AllArgsConstructor
@Tag(name="宣传推广")
public class PublicityController {
    @Autowired
    PublicityService publicityService;


    @GetMapping("/page")
    Result<PageResult<ReferenceVO>> page(@Valid PublicityQuery query){
        PageResult<ReferenceVO> page = publicityService.page(query);
        return Result.ok(page);
    }

    @GetMapping("/getDetail")
    Result<PageResult<UserVO>> detail(@Valid PublicityQuery query){
        PageResult<UserVO> page = publicityService.detail(query);
        return Result.ok(page);
    }
}
