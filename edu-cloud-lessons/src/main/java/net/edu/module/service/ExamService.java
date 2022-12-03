package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.vo.ExamAbilityVo;
import net.edu.module.vo.ExamVO;
import net.edu.module.query.ExamQuery;
import net.edu.module.entity.ExamEntity;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 考试
 *
 * @author 小樊 babamu@126.com
 * @since 1.0.0 2022-10-09
 */
public interface ExamService extends BaseService<ExamEntity> {

    PageResult<ExamVO> page(ExamQuery query);

    ExamEntity get(Long examId);

    void save(ExamVO vo);

    void update(ExamVO vo);

    void delete(List<Long> idList);

    PageResult<ExamVO> studentPage(ExamQuery query);

    List<ExamVO> getExamingList(Long userId);


    void updateExamIndex(Long examId);

    void submitPaper(Long examId, Long userId);

    void exportExam(Long examId, HttpServletResponse response) throws IOException;

    void exportUserExam(Long examId, List<Long> userIdList, HttpServletResponse response) throws IOException;

    ExamAbilityVo getAbilityExam();

    void promulgateGrade(Long examId, Long abilityId, Integer score);


    void downloadZip(HttpServletResponse response, Long problemId,
                     Integer problemType,
                     Integer source,
                     Long sourceId) throws IOException;

}
