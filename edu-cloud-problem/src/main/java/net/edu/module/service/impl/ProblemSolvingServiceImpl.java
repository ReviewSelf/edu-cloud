package net.edu.module.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.CodeProblemConvert;
import net.edu.module.convert.ProblemSolvingConvert;
import net.edu.module.dao.CodeProblemDao;
import net.edu.module.dao.ProblemSolvingDao;
import net.edu.module.entity.CodeProblemEntity;
import net.edu.module.entity.ProblemSolvingEntity;
import net.edu.module.query.ProblemSolvingQuery;
import net.edu.module.service.ProblemSolvingService;
import net.edu.module.vo.ProblemSolvingVO;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProblemSolvingServiceImpl extends BaseServiceImpl<ProblemSolvingDao, ProblemSolvingEntity> implements ProblemSolvingService {

    private final ProblemSolvingDao problemSolvingDao;

    @Override
    public PageResult<ProblemSolvingVO> getSolvingList(ProblemSolvingQuery query) {
        Page<ProblemSolvingVO> page = new Page<>(query.getPage(), query.getLimit());
        IPage<ProblemSolvingVO> list = problemSolvingDao.selectSolvingList(page, query);
        return new PageResult<>(list.getRecords(), list.getTotal());
    }

    @Override
    public ProblemSolvingVO getProblemSolving(Long id) {
        return problemSolvingDao.selectSolving(id);
    }

    @Override
    public void deleteProblemSolving(Long id) {
        problemSolvingDao.deleteProblemSolving(id);
    }

    @Override
    public void saveProblemSolving(ProblemSolvingVO vo) {
        ProblemSolvingEntity entity = ProblemSolvingConvert.INSTANCE.convert(vo);
        baseMapper.insert(entity);
    }

    @Override
    public void updateProblemSolving(ProblemSolvingVO vo) {
        ProblemSolvingEntity entity = ProblemSolvingConvert.INSTANCE.convert(vo);
        updateById(entity);
    }
}
