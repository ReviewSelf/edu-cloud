package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.ExamAttendLogEntity;
import net.edu.module.query.ExamAttendLogQuery;
import net.edu.module.vo.ExamAttendLogVO;

import java.util.Date;
import java.util.List;

/**
 * 课堂签到表
 *
 * @author 马佳浩 
 * @since 1.0.0 2022-09-15
 */
public interface ExamAttendLogService extends BaseService<ExamAttendLogEntity> {

    PageResult<ExamAttendLogVO> page(ExamAttendLogQuery query);

    void save(ExamAttendLogVO vo);


    void delete(List<Long> idList);

    ExamAttendLogVO getUserExamAttend(Long examId);

    List<ExamAttendLogVO> list(Long examId);

    Boolean attendance(Long examId, Long userId);

//    void save(ExamAttendLogVO vo);
//
    void update(ExamAttendLogVO vo);

    void copyFromClass(Long classId,Long examId);
    void updateExamStatus(Integer status,Long examId,Long userId);


    void updateAttendLog(ExamAttendLogVO vo);
}