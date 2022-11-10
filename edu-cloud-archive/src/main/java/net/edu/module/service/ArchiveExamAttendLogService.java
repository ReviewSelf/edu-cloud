package net.edu.module.service;

import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.ArchiveExamAttendLogEntity;


/**
 * @author weng
 * @date 2022/11/10 - 16:25
 **/
public interface ArchiveExamAttendLogService extends BaseService<ArchiveExamAttendLogEntity> {

    Integer insertExamAttendLog();
}
