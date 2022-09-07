package net.edu.module.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.EnrollClassEntity;
import net.edu.module.query.EnrollClassQuery;
import net.edu.module.vo.EnrollClassVO;


import java.util.List;

/**
 * 班级发布
 *
 * @author 翁瑞辰 
 * @since  2022-09-06
 */
public interface EnrollClassService extends BaseService<EnrollClassEntity> {

    IPage<EnrollClassEntity> getDailyReports(Integer pageIndex, Integer pageSize);

    void save(EnrollClassVO vo);

    void update(EnrollClassVO vo);

    void deleteEnrollClass(long id);
}