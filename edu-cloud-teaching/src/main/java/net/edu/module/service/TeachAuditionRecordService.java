package net.edu.module.service;

import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.TeachAuditionRecordEntity;
import net.edu.framework.common.page.PageResult;
import net.edu.module.query.TeachAuditionRecordQuery;
import net.edu.module.vo.TeachAuditionRecordVO;

import java.util.List;

/**
 * 教学试听安排
 *
 * @author sqw 
 * @since 1.0.0 2023-02-13
 */
public interface TeachAuditionRecordService extends BaseService<TeachAuditionRecordEntity> {

    PageResult<TeachAuditionRecordVO> page(TeachAuditionRecordQuery query);

    PageResult<TeachAuditionRecordVO> newStudentAuditionRecordPage(TeachAuditionRecordQuery query);

    void saveSaleAudition(TeachAuditionRecordVO vo);

    void save(TeachAuditionRecordVO vo);

    void update(TeachAuditionRecordVO vo);

    void delete(List<Long> idList);

    void joinAuditionLesson(TeachAuditionRecordVO vo);

    TeachAuditionRecordVO getAuditionRecord(Long id);

    void updateRecordStatus();

//    void saveNotLesson(TeachAuditionRecordVO vo);
}