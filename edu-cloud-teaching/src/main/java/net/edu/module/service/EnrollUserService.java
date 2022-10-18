package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.EnrollUserEntity;
import net.edu.module.entity.UserEntity;
import net.edu.module.query.EnrollUserQuery;
import net.edu.module.vo.EnrollUserVO;

import java.util.List;

/**
 * XinXiHeShi
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-09-05
 */
public interface EnrollUserService extends BaseService<EnrollUserEntity> {

    PageResult<EnrollUserVO> page(EnrollUserQuery query);

    void save(EnrollUserVO vo);

    void update(EnrollUserVO vo);

    void delete(List<Long> idList);

    void confirm(Integer id);

    void insertClassUser(Integer classId, String id);

    void insertOpenId(String openId, String unionId);

    void post(EnrollUserVO enrollUserVO);

    EnrollUserVO selectUserInfoByOpenId(String openId);
}
