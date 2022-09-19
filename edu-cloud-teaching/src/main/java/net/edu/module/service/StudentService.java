package net.edu.module.service;


import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.UserEntity;
import net.edu.module.query.RoleUserQuery;
import net.edu.module.query.UserQuery;
import net.edu.module.vo.UserVO;

import java.util.List;

/**
 * 用户管理
 *
 * @author 阿沐 babamu@126.com
 */
public interface StudentService extends BaseService<UserEntity> {

    PageResult<UserVO> SelectStudentList(UserQuery query);

    void save(UserVO vo);

    void update(UserVO vo);

    void delete(List<Long> idList);

    UserVO getByMobile(String mobile);

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
    PageResult<UserVO> roleUserPage(RoleUserQuery query);

}
