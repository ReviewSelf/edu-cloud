package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.UserConvert;
import net.edu.module.entity.TeachClassHoursEntity;
import net.edu.module.entity.UserEntity;
import net.edu.module.query.UserQuery;
import net.edu.module.service.UserService;
import net.edu.module.vo.TeachClassHoursVO;
import net.edu.module.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody UserVO vo){
        userService.update(vo);

        return Result.ok();
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody @Valid UserVO vo){
        userService.save(vo);
        return Result.ok();
    }


    @GetMapping("/{id}")
    @Operation(summary = "信息")
    public Result<UserVO> getStudent(@PathVariable("id") Long id){
        UserEntity entity = userService.getById(id);
        UserVO vo = UserConvert.INSTANCE.convert(entity);
        return Result.ok(vo);
    }

    @GetMapping("status")
    @Operation(summary = "漏斗统计")
    public Result<List<Integer>> getFunnelData(){
        List<Integer> integers = userService.selectUserStatus();
        return Result.ok(integers);
    }

    @GetMapping("pay/{id}")
    @Operation(summary = "课时信息")
    public Result<TeachClassHoursVO> getStudentPay(@PathVariable("id") Long id){

        TeachClassHoursVO vo = userService.getStudentPay(id);
        return Result.ok(vo);
    }

    @GetMapping("sale")
    @Operation(summary = "销售名单")
    public Result<List<UserVO>> selectSaleName(){
        return Result.ok(userService.selectSaleName());
    }

    @GetMapping("teach/class")
    @Operation(summary = "新建用户课时记录")
    public void insertTeachClassHours(@RequestParam("userId") Long userId){
        userService.insertTeachClassHours(userId);
    }
}