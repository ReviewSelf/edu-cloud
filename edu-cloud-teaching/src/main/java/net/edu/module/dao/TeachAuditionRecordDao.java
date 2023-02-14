package net.edu.module.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.TeachAuditionRecordEntity;
import net.edu.module.query.TeachAuditionRecordQuery;
import net.edu.module.vo.TeachAuditionRecordVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* 教学试听安排
*
* @author sqw 
* @since 1.0.0 2023-02-13
*/
@Mapper
public interface TeachAuditionRecordDao extends BaseDao<TeachAuditionRecordEntity> {

    IPage<TeachAuditionRecordEntity> selectAuditionRecordPage(IPage<TeachAuditionRecordEntity> page, @Param("query") TeachAuditionRecordQuery query);

    void insertLessonAttendLog(@Param("vo") TeachAuditionRecordVO vo);
}