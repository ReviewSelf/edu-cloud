package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.ExamAttendLogEntity;
import net.edu.module.query.ExamAttendLogQuery;
import net.edu.module.vo.ExamAttendLogVO;

import java.util.Date;
import java.util.List;
import java.util.Map;

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


    void update(ExamAttendLogVO vo);

    void copyFromClass(List<Long> classIdList,Long examId);
    void updateExamStatus(Integer status,Long examId,Long userId,Date date);


    void updateAttendLogScore(ExamAttendLogVO vo);

    List<ExamAttendLogVO> getList(Long examId,Integer status,Integer isCorrecting);

    void genExamInvitationCode(Long examId,String code,Long time);

    void receiveExamInvitation(String code);

    ExamAttendLogVO getUserExamInfo(Long userId,Long examId);

    Map<String ,String> getStudentExamStatisticsInfo(Long userId);

    Map<String, String> getTeacherExamStatisticsInfo(Long userId);
}