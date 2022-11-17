package net.edu.module.service;


import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.ArchiveWeightTargetCourseEntity;
import net.edu.module.vo.ArchivePointAndTargetVO;
import net.edu.module.vo.ArchiveWeightTargetCourseVO;
import net.edu.module.query.ArchiveWeightTargetCourseQuery;

import java.util.List;

/**
 * 一级知识点权重
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-29
 */
public interface ArchiveWeightTargetCourseService extends BaseService<ArchiveWeightTargetCourseEntity> {

    PageResult<ArchiveWeightTargetCourseVO> page(ArchiveWeightTargetCourseQuery query);

    void save(ArchiveWeightTargetCourseVO vo);

    void update(ArchiveWeightTargetCourseVO vo);

    void delete(Long targetId,Long courseId);

    List<ArchiveWeightTargetCourseVO> selectCourseByTargetId(Long targetId);

    Integer insertCourseWeight(List<ArchiveWeightTargetCourseVO> archiveWeightTargetCourseVO);

    ArchiveWeightTargetCourseVO selectById(Long id);

    List<ArchivePointAndTargetVO> selectPointAndTarget(Long courseId);

}
