package net.edu.module.dao;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ArchiveAssessEntity;
import net.edu.module.entity.ArchiveWeightGoalEntity;
import net.edu.module.query.ArchiveAssessQuery;
import net.edu.module.vo.ArchiveAssessVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* 考核点
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-26
*/
@Mapper
public interface ArchiveWeightGoalDao extends BaseDao<ArchiveAssessEntity> {

    List<ArchiveWeightGoalEntity> selectGoalByTargetId(Long targetId);

    List<ArchiveWeightGoalEntity> selectGoalByCourseId(Long courseId);

}
