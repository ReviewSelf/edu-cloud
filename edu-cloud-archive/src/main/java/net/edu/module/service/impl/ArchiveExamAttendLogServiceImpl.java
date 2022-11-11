package net.edu.module.service.impl;

import lombok.AllArgsConstructor;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.dao.ArchiveExamAttendLogDao;
import net.edu.module.entity.ArchiveExamAttendLogEntity;
import net.edu.module.service.ArchiveExamAttendLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author weng
 * @date 2022/11/10 - 16:25
 **/
@Service
@AllArgsConstructor
public class ArchiveExamAttendLogServiceImpl extends BaseServiceImpl<ArchiveExamAttendLogDao, ArchiveExamAttendLogEntity>  implements ArchiveExamAttendLogService {
    @Autowired
    private ArchiveExamAttendLogDao archiveExamAttendLogDao;

    @Override
    public Integer insertExamAttendLog() {
        return archiveExamAttendLogDao.insertExamAttendLog();
    }
}