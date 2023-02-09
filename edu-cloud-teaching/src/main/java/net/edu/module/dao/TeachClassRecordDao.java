package net.edu.module.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.TeachClassRecordEntity;
import net.edu.module.query.TeachClassRecordQuery;
import net.edu.module.vo.ClassUserRecordVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TeachClassRecordDao extends BaseDao<TeachClassRecordEntity> {
    void insertClassUserRecord(@Param("vo") ClassUserRecordVO vo);

    IPage<TeachClassRecordEntity> selectClassRecordPage(IPage<TeachClassRecordEntity> page, @Param("query") TeachClassRecordQuery query);
}
