package net.edu.module.service.impl;


import lombok.AllArgsConstructor;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.entity.ProblemResourceEntity;
import net.edu.module.vo.ProblemResourceVO;
import net.edu.module.dao.ProblemResourceDao;
import net.edu.module.service.ProblemResourceService;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * 问题资源表
 *
 * @author 周建超 
 * @since 1.0.0 2022-09-20
 */
@Service
@AllArgsConstructor
public class ProblemResourceServiceImpl extends BaseServiceImpl<ProblemResourceDao, ProblemResourceEntity> implements ProblemResourceService {


    @Override
    public List<ProblemResourceVO> getProblemResource(Long id) {
        return baseMapper.selectProblemResource(id);
    }

    @Override
    public void deleteProblemResource(Long id) {
        baseMapper.deleteProblemResource(id);
    }

    @Override
    public void saveProblemResource(ProblemResourceVO vo) {
        baseMapper.insertProblemResource(vo);
    }

}