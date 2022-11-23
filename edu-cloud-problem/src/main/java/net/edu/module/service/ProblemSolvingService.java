package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.module.query.ProblemSolvingQuery;
import net.edu.module.vo.ProblemSolvingVO;


public interface ProblemSolvingService {
    PageResult<ProblemSolvingVO> getSolvingList(ProblemSolvingQuery query);

    ProblemSolvingVO getProblemSolving(Long id);

    void deleteProblemSolving(Long id);

    void saveProblemSolving(ProblemSolvingVO vo);

    void updateProblemSolving(ProblemSolvingVO vo);
}
