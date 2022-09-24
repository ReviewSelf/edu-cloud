package net.edu.module.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.EnrollClassEntity;
import net.edu.module.query.EnrollClassQuery;
import net.edu.module.query.EnrollUserQuery;
import net.edu.module.vo.EnrollClassVO;
import net.edu.module.vo.EnrollUserVO;


import java.util.List;

/**
 * 班级发布
 *
 * @author 翁瑞辰 
 * @since  2022-09-06
 */
public interface EnrollClassService extends BaseService<EnrollClassEntity> {

    PageResult<EnrollClassVO> page(EnrollClassQuery query);

    void save(EnrollClassVO vo);

    void update(EnrollClassVO vo);

    void delete(List<Long> idList);

    void updateStatus(Long id);
    List<EnrollClassVO> selectPublish();

}