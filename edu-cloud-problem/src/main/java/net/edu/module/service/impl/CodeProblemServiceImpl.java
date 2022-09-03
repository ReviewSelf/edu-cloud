package net.edu.module.service.impl;

import net.edu.framework.common.page.PageResult;
import net.edu.module.entity.CodeProblemEntity;
import net.edu.module.query.ProblemQuery;
import net.edu.module.service.CodeProblemService;
import net.edu.module.service.ProblemService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/3 17:35
 * @Version: 1.0
 * @Description:
 */
@Service
public class CodeProblemServiceImpl implements CodeProblemService {

    @Override
    public void savaProblem(CodeProblemEntity object) {

    }

    @Override
    public void delProblem(Integer problemId) {

    }

    @Override
    public void delProblemBatch(List<Integer> problemIdList) {

    }

    @Override
    public void updateProblem(CodeProblemEntity object) {

    }

    @Override
    public CodeProblemEntity getProblemDetail(Integer problemId) {
        return null;
    }

    @Override
    public PageResult<CodeProblemEntity> getProblemPage(ProblemQuery query) {
        return null;
    }

    @Override
    public void changeState(Integer problemId, Integer stateCode) {

    }

    @Override
    public void changeStateBatch(List<Integer> problemIdList, Integer stateCode) {

    }
}
