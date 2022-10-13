package net.edu.module.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.framework.security.user.SecurityUser;
import net.edu.module.convert.UserConvert;
import net.edu.module.entity.UserEntity;
import net.edu.module.query.UserQuery;
import net.edu.module.service.UserRoleService;
import net.edu.module.service.StudentService;
import net.edu.module.vo.StudentsVo;
import net.edu.module.vo.TeachStudentVo;
import net.edu.module.vo.UserVO;
import netscape.javascript.JSObject;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;


/**
 * 用户管理
 *
 * @author 阿沐 babamu@126.com
 */
@RestController
@RequestMapping("student")
@AllArgsConstructor
@Tag(name="用户管理")
public class TeachStudentController {
    private final StudentService userService;
    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/import")
    public Result<String> studentFromExcel(@RequestParam("file") MultipartFile file) {
        userService.studentFromExcel(file);
        return Result.ok();
    }

    @GetMapping("/page")
    @Operation(summary = "分页")
    public Result<PageResult<UserVO>> StudentPage( @Valid UserQuery query){
        PageResult<UserVO> page = userService.SelectStudentList(query);
        return Result.ok(page);
    }

    @PostMapping("/getStudents")
    @Operation(summary = "学生列表")

    public Object getStudents(@RequestBody StudentsVo vo){

        System.out.println(vo);
        PageResult<TeachStudentVo> page = userService.getStudents(vo);

        return Result.ok(page);
    }

    @GetMapping("/{id}")
    @Operation(summary = "信息")

    public Result<UserVO> getStudent(@PathVariable("id") Long id){
        UserEntity entity = userService.getById(id);

        UserVO vo = UserConvert.INSTANCE.convert(entity);

        // 用户角色列表
        List<Long> roleIdList = userRoleService.getRoleIdList(id);
        vo.setRoleIdList(roleIdList);

        return Result.ok(vo);
    }

    @PostMapping
    @Operation(summary = "保存")

    public Result<String> save(@RequestBody @Valid UserVO vo){
        // 新增密码不能为空
        if (StrUtil.isBlank(vo.getPassword())){
            Result.error("密码不能为空");
        }

        // 密码加密
        vo.setPassword(passwordEncoder.encode(vo.getPassword()));

        // 保存
        userService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")

    public Result<String> update(@RequestBody @Valid UserVO vo){
        // 如果密码不为空，则进行加密处理
        if(StrUtil.isBlank(vo.getPassword())){
            vo.setPassword(null);
        }else{
            vo.setPassword(passwordEncoder.encode(vo.getPassword()));
        }

        userService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")

    public Result<String> delete(@RequestBody List<Long> idList){
        Long userId = SecurityUser.getUserId();
        if(idList.contains(userId)){
            return Result.error("不能删除当前登录用户");
        }
        userService.delete(idList);

        return Result.ok();
    }

    @GetMapping("/updateSubmitCorrectTimes")
    @Operation(summary = "修改提交次数和正确次数")
    public Result<String> updateSubmitCorrectTimes(@RequestParam Long userId,@RequestParam Integer correct){
        userService.updateSubmitCorrectTimes(userId,correct);
        return Result.ok();
    }
}
