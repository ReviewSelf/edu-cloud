package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.query.EnrollQuery;
import net.edu.module.query.UserQuery;
import net.edu.module.service.UserService;
import net.edu.module.vo.EnrollUserVO;
import net.edu.module.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author weng
 * @date 2023/1/13 - 13:20
 **/
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<UserVO>> page(@Valid UserQuery query){
        PageResult<UserVO> page = userService.page(query);
        return Result.ok(page);
    }

    @PostMapping("cadets")
    @Operation(summary = "成为学员")
    public Result<String> updateCadets(@RequestBody UserVO vo){
        userService.insertCadet(vo);

        return Result.ok();
    }
}