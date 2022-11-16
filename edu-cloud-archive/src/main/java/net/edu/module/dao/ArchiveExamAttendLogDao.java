package net.edu.module.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ArchiveExamAttendLogEntity;
import net.edu.module.entity.ArchiveExamEntity;
import net.edu.module.vo.ArchiveExamAttendLogVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author weng
 * @date 2022/11/10 - 16:05
 **/
@Mapper
public interface ArchiveExamAttendLogDao extends BaseDao<ArchiveExamAttendLogEntity> {

    Integer insertExamAttendLog();

    List<ArchiveExamAttendLogVO> selectExamAttendLogByExamId(Long examId);

    ArchiveExamAttendLogVO selectById(Long id);
}
