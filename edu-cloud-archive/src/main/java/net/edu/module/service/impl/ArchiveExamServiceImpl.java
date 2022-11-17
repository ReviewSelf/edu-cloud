package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.ArchiveExamConvert;
import net.edu.module.dao.ArchiveExamDao;
import net.edu.module.entity.ArchiveExamEntity;
import net.edu.module.query.ArchiveExamQuery;
import net.edu.module.service.ArchiveExamService;
import net.edu.module.vo.ArchiveExamVO;
import net.edu.module.vo.ArchiveTargetVO;
import net.edu.module.vo.ClassVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author weng
 * @date 2022/11/10 - 16:25
 **/
@Service
@AllArgsConstructor
public class ArchiveExamServiceImpl extends BaseServiceImpl<ArchiveExamDao, ArchiveExamEntity>  implements ArchiveExamService {
    @Autowired
    private ArchiveExamDao archiveExamDao;


    @Override
    public PageResult<ArchiveExamVO> page(ArchiveExamQuery query) {
        Page<ArchiveExamVO> page = new Page<>(query.getPage(), query.getLimit());
        IPage<ArchiveExamVO> list = archiveExamDao.selectExamByPage(page, query);
        return new PageResult<>(list.getRecords(), list.getTotal());
    }

    @Override
    public ArchiveExamVO selectExamById(Long id) {
        return archiveExamDao.selectExamById(id);
    }

    @Override
    public List<ArchiveExamVO> selectExamByCourseId(Long courseId,Long classId) {
        return archiveExamDao.selectExamByCourseId(courseId,classId);
    }

    @Override
    public List<ClassVO> selectClassByCourseId(Long courseId) {
        return archiveExamDao.selectClassByCourseId(courseId);
    }

    @Override
    public Integer insertExam() {
        return archiveExamDao.insertExam();
    }
}