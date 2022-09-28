package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.TeachClassUserEntity;
import net.edu.module.entity.TeachStudentEntity;
import net.edu.module.query.TeachClassUserQuery;
import net.edu.module.vo.TeachClassStudentVo;
import net.edu.module.vo.TeachClassUserVO;
import net.edu.module.vo.UserVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.Date;
import java.util.List;

/**
 * 班级用户表
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-09-16
 */
@Service
public interface TeachClassUserService extends BaseService<TeachClassUserEntity> {

    PageResult<TeachClassUserVO> page(TeachClassUserQuery query);

    List<Long> getUserIdList(Long classId);

    void save(TeachClassUserVO vo);

    void update(TeachClassUserVO vo);

    void delete(List<Long> idList);

    void quitClass(Long classId,Long userId, Date quitTime);

    List<TeachStudentEntity> getStudent(Long id);
}