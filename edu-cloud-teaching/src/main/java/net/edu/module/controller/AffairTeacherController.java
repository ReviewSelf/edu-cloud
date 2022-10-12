package net.edu.module.controller;

import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.framework.security.user.SecurityUser;
import net.edu.module.convert.AffairTeacherConvert;
import net.edu.module.entity.UserEntity;
import net.edu.module.query.AffairTeacherQuery;
import net.edu.module.service.RoleService;
import net.edu.module.service.AffairTeacherService;
import net.edu.module.vo.PasswordVo;
import net.edu.module.vo.AffairTeacherVO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description: TODO
 * @author: sl
 * @date: 2022年10月10日 16:47
 */
@RestController
@RequestMapping("affairTeacher")
@AllArgsConstructor
@Tag(name="教务管理老师用户管理")
public class AffairTeacherController {

    private final AffairTeacherService affairTeacherService;

    private final PasswordEncoder passwordEncoder;

    private final RoleService roleService;

    @GetMapping("affairTeacherPage")
    @Operation(summary = "分页")
    public Result<PageResult<AffairTeacherVO>> affairTeacherPage(@Valid AffairTeacherQuery query) {
        PageResult<AffairTeacherVO> page = affairTeacherService.affairTeacherPage(query);
        return Result.ok(page);
    }

    @DeleteMapping
    @Operation(summary = "删除")

    public Result<String> delete(@RequestBody List<Long> idList) {
        Long userId = SecurityUser.getUserId();
        if (idList.contains(userId)) {
            return Result.error("不能删除当前登录用户");
        }
        affairTeacherService.delete(idList);

        return Result.ok();
    }

    @PutMapping("ResetPassword")
    @Operation(summary = "重置密码")
    public Result<String> ResetPassword(@RequestBody PasswordVo vo) {
        vo.setPassword(passwordEncoder.encode(vo.getPassword()));
        affairTeacherService.resetPassword(vo.getId(), vo.getPassword());
        return Result.ok();
    }


    @GetMapping("affairTeacher/{id}")
    @Operation(summary = "信息")
    public Result<AffairTeacherVO> get(@PathVariable("id") Long id) {
        UserEntity entity = affairTeacherService.getById(id);

        AffairTeacherVO vo = AffairTeacherConvert.INSTANCE.convert(entity);

        // 用户角色列表
        List<Long> roleIdList = roleService.getRoleIdList(id);
        vo.setRoleIdList(roleIdList);
        return Result.ok(vo);
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody @Valid AffairTeacherVO vo) {
        // 新增密码不能为空
        if (StrUtil.isBlank(vo.getPassword())) {
            Result.error("密码不能为空");
        }

        vo.setPassword("123456");
        // 密码加密
        vo.setPassword(passwordEncoder.encode(vo.getPassword()));

        // 保存
        affairTeacherService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")

    public Result<String> update(@RequestBody @Valid AffairTeacherVO vo) {
        // 如果密码不为空，则进行加密处理
        if (StrUtil.isBlank(vo.getPassword())) {
            vo.setPassword(null);
        } else {
            vo.setPassword(passwordEncoder.encode(vo.getPassword()));
        }

        affairTeacherService.update(vo);

        return Result.ok();
    }
}
