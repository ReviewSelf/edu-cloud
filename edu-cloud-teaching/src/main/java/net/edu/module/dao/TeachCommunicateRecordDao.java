package net.edu.module.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.TeachCommunicateRecordEntity;
import net.edu.module.query.TeachCommunicateRecordQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* 沟通记录表
*
* @author sqw 
* @since 1.0.0 2023-02-05
*/
@Mapper
public interface TeachCommunicateRecordDao extends BaseDao<TeachCommunicateRecordEntity> {

    IPage<TeachCommunicateRecordEntity> selectCommunicateRecordPage(IPage<TeachCommunicateRecordEntity> page, @Param("query") TeachCommunicateRecordQuery query);
}