package net.edu.module.service;


import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.UserEntity;
import net.edu.module.query.RoleUserQuery;
import net.edu.module.query.UserQuery;
import net.edu.module.vo.OrgVo;
import net.edu.module.vo.StudentsVO;
import net.edu.module.vo.TeachStudentVO;
import net.edu.module.vo.UserVO;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 用户管理
 *
 * @author 阿沐 babamu@126.com
 */
public interface StudentService extends BaseService<UserEntity> {

    PageResult<UserVO> SelectStudentList(UserQuery query);

    Long save(UserVO vo);

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

    void updateSubmitCorrectTimes(Long userId,Integer correct);

    void studentFromExcel(MultipartFile file);

    /**
     * 获取学生id
     */
    String getStudentId(String unionId);

    List<OrgVo> getOrgList();

    PageResult<TeachStudentVO> getStudents(StudentsVO vo);

    void  renewAmountSubmit( Long userId, Float num ,String remarks);

    void outClassSubmit(Long userId, Long classId, Float num, String remarks);

    void joinClassSubmit(Long userId, Long classId, Float num, String remarks);
}
