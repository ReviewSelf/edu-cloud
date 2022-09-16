package net.edu.module.service;


import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.UserEntity;
import net.edu.module.query.RoleQuery;
import net.edu.module.query.TeacherQuery;
import net.edu.module.vo.TeacherVO;

import java.util.List;

/**
 * 用户管理
 *
 * @author 阿沐 babamu@126.com
 */
public interface TeacherService extends BaseService<UserEntity> {


    PageResult<TeacherVO> TeacherPage(TeacherQuery query);

    void save(TeacherVO vo);

    void update(TeacherVO vo);

    void delete(List<Long> idList);

    TeacherVO getByMobile(String mobile);

    /**
     * 修改密码
     *
     * @param id          用户ID
     * @param newPassword 新密码
     */
    void updatePassword(Long id, String newPassword);

    /**
     * 分配角色，用户列表
     */
    PageResult<TeacherVO> roleUserPage(RoleQuery query);

}
