package net.edu.module.controller;

import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.framework.security.user.SecurityUser;
import net.edu.module.convert.TeacherConvert;
import net.edu.module.entity.UserEntity;
import net.edu.module.query.TeacherQuery;
import net.edu.module.service.RoleService;
import net.edu.module.service.TeacherService;
import net.edu.module.vo.AllTeacherVo;
import net.edu.module.vo.PasswordVo;
import net.edu.module.vo.TeacherVO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * 用户管理
 *
 * @author 阿沐 babamu@126.com
 */
@RestController
@RequestMapping("user")
@AllArgsConstructor
@Tag(name="用户管理")
public class TeacherController {
    private final TeacherService teacherService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("teacherPage")
    @Operation(summary = "分页")

    public Result<PageResult<TeacherVO>> TeacherPage(@Valid TeacherQuery query) {
        PageResult<TeacherVO> page = teacherService.TeacherPage(query);
        return Result.ok(page);
    }

    @GetMapping("teacher/{id}")
    @Operation(summary = "信息")

    public Result<TeacherVO> get(@PathVariable("id") Long id) {
        UserEntity entity = teacherService.getById(id);

        TeacherVO vo = TeacherConvert.INSTANCE.convert(entity);

        // 用户角色列表
        List<Long> roleIdList = roleService.getRoleIdList(id);
        vo.setRoleIdList(roleIdList);
        System.out.println(entity);
        System.out.println(roleIdList);
        return Result.ok(vo);
    }

    @PostMapping
    @Operation(summary = "保存")

    public Result<String> save(@RequestBody @Valid TeacherVO vo) {
        // 新增密码不能为空
        if (StrUtil.isBlank(vo.getPassword())) {
            Result.error("密码不能为空");
        }

        vo.setPassword("123456");
        // 密码加密
        vo.setPassword(passwordEncoder.encode(vo.getPassword()));

        // 保存
        teacherService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid TeacherVO vo) {
        // 如果密码不为空，则进行加密处理
        if (StrUtil.isBlank(vo.getPassword())) {
            vo.setPassword(null);
        } else {
            vo.setPassword(passwordEncoder.encode(vo.getPassword()));
        }

        teacherService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")

    public Result<String> delete(@RequestBody List<Long> idList) {
        Long userId = SecurityUser.getUserId();
        if (idList.contains(userId)) {
            return Result.error("不能删除当前登录用户");
        }
        teacherService.delete(idList);

        return Result.ok();
    }

    @PutMapping("ResetPassword")
    @Operation(summary = "重置密码")
    public Result<String> ResetPassword(@RequestBody PasswordVo vo) {
        vo.setPassword(passwordEncoder.encode(vo.getPassword()));
        teacherService.resetPassword(vo.getId(), vo.getPassword());
        return Result.ok();
    }

    @GetMapping("GetTeacher")
    @Operation(summary= "获取全部老师")
    public Result<List<AllTeacherVo>> GetTeacher(){
        List<AllTeacherVo> allTeacherVo=teacherService.GetTeacher();
        return Result.ok(allTeacherVo);
    }

}
