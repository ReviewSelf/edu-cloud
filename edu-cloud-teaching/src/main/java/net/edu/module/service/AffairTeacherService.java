package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.UserEntity;
import net.edu.module.query.AffairTeacherQuery;
import net.edu.module.vo.AffairTeacherVO;

import java.util.List;

public interface AffairTeacherService extends BaseService<UserEntity> {
    PageResult<AffairTeacherVO> affairTeacherPage(AffairTeacherQuery query);

    void resetPassword(String id,String password);

    void delete(List<Long> idList);

    void save(AffairTeacherVO vo);

    void update(AffairTeacherVO vo);
}
