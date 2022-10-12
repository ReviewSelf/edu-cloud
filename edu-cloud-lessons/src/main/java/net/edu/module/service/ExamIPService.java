package net.edu.module.service;


import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.ExamIPEntity;
import net.edu.module.query.ExamIPQuery;
import net.edu.module.vo.ExamIPVO;

import java.util.List;

public interface ExamIPService  extends BaseService<ExamIPEntity> {
    List<ExamIPVO> list(ExamIPQuery query);

    void save(ExamIPVO vo);

    void update(ExamIPVO vo);

    void delete(List<Long> idList);

    Boolean ipJudge(Long ExamId,String ip);
}
