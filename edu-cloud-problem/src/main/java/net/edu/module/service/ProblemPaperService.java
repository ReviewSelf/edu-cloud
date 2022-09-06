package net.edu.module.service;



import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.vo.ProblemPaperVO;
import net.edu.module.query.ProblemPaperQuery;
import net.edu.module.entity.ProblemPaperEntity;

import java.util.List;

/**
 * 问题卷表
 *
 * @author 樊磊 
 * @since 1.0.0 2022-09-05
 */
public interface ProblemPaperService extends BaseService<ProblemPaperEntity> {

    PageResult<ProblemPaperVO> page(ProblemPaperQuery query);

    void save(ProblemPaperVO vo);

    void update(ProblemPaperVO vo);

    void delete(List<Long> idList);
}