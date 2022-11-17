package net.edu.module.dao;


import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ArchiveWeightTargetCourseEntity;
import net.edu.module.vo.ArchiveWeightTargetCourseVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* 一级知识点权重
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-29
*/
@Mapper
public interface ArchiveWeightTargetCourseDao extends BaseDao<ArchiveWeightTargetCourseEntity> {

    List<ArchiveWeightTargetCourseVO> selectCourseByTargetId(Long targetId);

    Integer insertCourseWeight(List<ArchiveWeightTargetCourseVO> archiveWeightTargetCourseVO);


    void updateDeleted(Long targetId,Long courseId);

    Integer updateDeletedByTarget(Long targetId);

    ArchiveWeightTargetCourseVO getById(Long id);

    List<ArchiveWeightTargetCourseVO> selectCourseByCourseId(Long courseId);
}
