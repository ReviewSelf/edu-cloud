package net.edu.module.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.TeachClassHoursFlowRecordEntity;
import net.edu.module.query.TeachClassHoursFlowRecordQuery;
import net.edu.module.vo.TeachClassHoursFlowRecordVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* 课时流水管理
*
* @author sqw 
* @since  2023-03-06
*/
@Mapper
public interface TeachClassHoursFlowRecordDao extends BaseDao<TeachClassHoursFlowRecordEntity> {

    IPage<TeachClassHoursFlowRecordVO> selectFlowRecordPage(IPage<TeachClassHoursFlowRecordEntity> page,@Param("query") TeachClassHoursFlowRecordQuery query);

    TeachClassHoursFlowRecordEntity getByLessonIdAndStudId(Long lessonId, Long stuId);
}