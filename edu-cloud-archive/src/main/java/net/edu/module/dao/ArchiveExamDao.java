package net.edu.module.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ArchiveExamEntity;
import net.edu.module.query.ArchiveExamQuery;
import net.edu.module.query.ArchiveTargetQuery;
import net.edu.module.vo.ArchiveExamVO;
import net.edu.module.vo.ArchiveTargetVO;
import net.edu.module.vo.ClassVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author weng
 * @date 2022/11/10 - 16:05
 **/
@Mapper
public interface ArchiveExamDao extends BaseDao<ArchiveExamEntity> {

    Integer insertExam();

    IPage<ArchiveExamVO> selectExamByPage(Page<ArchiveExamVO> page, @Param("query") ArchiveExamQuery query);

    ArchiveExamVO selectExamById(Long id);

    List<ArchiveExamVO> selectExamByCourseId(Long courseId,Long classId);

    List<ClassVO> selectClassByCourseId(Long courseId);
}
