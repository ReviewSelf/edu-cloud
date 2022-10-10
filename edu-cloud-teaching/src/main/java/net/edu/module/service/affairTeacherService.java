package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.UserEntity;
import net.edu.module.query.AffairTeacherQuery;
import net.edu.module.query.TeacherQuery;
import net.edu.module.vo.TeacherVO;
import net.edu.module.vo.affairTeacherVO;

import java.util.List;

public interface affairTeacherService extends BaseService<UserEntity> {
    PageResult<affairTeacherVO> affairTeacherPage(AffairTeacherQuery query);

    void resetPassword(String id,String password);

    void delete(List<Long> idList);

    void save(affairTeacherVO vo);

    void update(affairTeacherVO vo);
}
