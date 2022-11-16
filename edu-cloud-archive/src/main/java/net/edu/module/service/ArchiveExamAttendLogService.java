package net.edu.module.service;

import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.ArchiveExamAttendLogEntity;
import net.edu.module.vo.ArchiveExamAttendLogVO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * @author weng
 * @date 2022/11/10 - 16:25
 **/
public interface ArchiveExamAttendLogService extends BaseService<ArchiveExamAttendLogEntity> {

    Integer insertExamAttendLog();

    List<ArchiveExamAttendLogVO> selectExamAttendLogByExamId(Long examId);

    void exportExam(Long examId, HttpServletResponse response) throws IOException;
}
