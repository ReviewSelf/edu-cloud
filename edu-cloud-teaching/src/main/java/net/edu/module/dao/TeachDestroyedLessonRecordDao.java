package net.edu.module.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.TeachDestroyedLessonRecordEntity;
import net.edu.module.query.TeachDestroyedLessonRecordQuery;
import net.edu.module.vo.TeachDestroyedLessonRecordVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* 消课管理
*
* @author sqw 
* @since 1.0.0 2023-03-04
*/
@Mapper
public interface TeachDestroyedLessonRecordDao extends BaseDao<TeachDestroyedLessonRecordEntity> {

    IPage<TeachDestroyedLessonRecordVO> selectDestroyedLessonRecordPage(IPage<TeachDestroyedLessonRecordEntity> page,@Param("query") TeachDestroyedLessonRecordQuery query);

    void addRecord(@Param("vo") TeachDestroyedLessonRecordVO vo);
}