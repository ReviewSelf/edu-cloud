package net.edu.module.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ArchiveExamEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author weng
 * @date 2022/11/10 - 16:05
 **/
@Mapper
public interface ArchiveExamDao extends BaseDao<ArchiveExamEntity> {

    Integer insertExam();
}
