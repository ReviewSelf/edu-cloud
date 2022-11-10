package net.edu.module.service.impl;

import lombok.AllArgsConstructor;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.dao.ArchiveExamDao;
import net.edu.module.entity.ArchiveExamEntity;
import net.edu.module.service.ArchiveExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Integer insertExam() {
        return archiveExamDao.insertExam();
    }
}