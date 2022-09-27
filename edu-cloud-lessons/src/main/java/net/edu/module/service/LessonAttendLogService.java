package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.LessonAttendLogEntity;
import net.edu.module.query.LessonAttendLogQuery;
import net.edu.module.vo.LessonAttendLogVO;


import java.util.List;

/**
 * 课堂签到表
 *
 * @author 马佳浩 
 * @since 1.0.0 2022-09-15
 */
public interface LessonAttendLogService extends BaseService<LessonAttendLogEntity> {

    List<LessonAttendLogVO> list(LessonAttendLogQuery query);

    Result attendance(Long userId, Long lessonId);

    void save(LessonAttendLogVO vo);

    void update(LessonAttendLogVO vo);

    void delete(List<Long> idList);

    void copyUserFromClassUser(List<Long> userList,Long lessonId);

    void updateStudents(LessonAttendLogVO vo);
}