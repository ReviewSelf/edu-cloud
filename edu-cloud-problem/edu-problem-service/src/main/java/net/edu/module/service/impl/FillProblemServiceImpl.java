package net.edu.module.service.impl;

import net.edu.framework.common.page.PageResult;
import net.edu.module.entity.FillProblemEntity;
import net.edu.module.query.ProblemQuery;
import net.edu.module.service.FillProblemService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/3 18:38
 * @Version: 1.0
 * @Description:
 */
@Service
public class FillProblemServiceImpl implements FillProblemService {

    @Override
    public void savaProblem(FillProblemEntity object) {

    }

    @Override
    public void delProblem(Integer problemId) {

    }

    @Override
    public void delProblemBatch(List<Integer> problemIdList) {

    }

    @Override
    public void updateProblem(FillProblemEntity object) {

    }

    @Override
    public FillProblemEntity getProblemDetail(Integer problemId) {
        return null;
    }

    @Override
    public PageResult<FillProblemEntity> getProblemPage(ProblemQuery query) {
        return null;
    }

    @Override
    public void changeState(Integer problemId, Integer stateCode) {

    }

    @Override
    public void changeStateBatch(List<Integer> problemIdList, Integer stateCode) {

    }
}
