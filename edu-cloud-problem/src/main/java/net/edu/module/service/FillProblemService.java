package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.vo.FillProblemVO;
import net.edu.module.query.FillProblemQuery;
import net.edu.module.entity.FillProblemEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 填空题表
 *
 * @author 马佳浩 
 * @since 1.0.0 2022-09-05
 */
public interface FillProblemService extends BaseService<FillProblemEntity> {

    PageResult<FillProblemVO> page(FillProblemQuery query);

    void save(FillProblemVO vo);

    void update(FillProblemVO vo);

    void delete(List<Long> idList);

    void updateStatus(Long problemId);

    void updateUsedNum(Long id);

    void updateSubmitTimes(Long id, Boolean isTrue);

    FillProblemVO selectFillProblemInfo(Long id);

    void importFromExcel(MultipartFile file);
}