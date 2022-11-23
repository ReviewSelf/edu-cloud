package net.edu.module.service;


import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.vo.ArchiveCourseVO;
import net.edu.module.query.ArchiveCourseQuery;
import net.edu.module.entity.ArchiveCourseEntity;

import java.util.List;

/**
 * 能力课程
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-25
 */
public interface ArchiveCourseService extends BaseService<ArchiveCourseEntity> {

    PageResult<ArchiveCourseVO> page(ArchiveCourseQuery query);

    void save(ArchiveCourseVO vo);

    void update(ArchiveCourseVO vo);

    void delete(List<Long> idList);

    List<ArchiveCourseVO> selectArchiveCourseAll();

    List<ArchiveCourseVO> selectArchiveCourse(String grade);
}
