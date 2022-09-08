package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.ProblemCodeSampleConvert;
import net.edu.module.dao.CodeProblemDao;
import net.edu.module.dao.ProblemCodeSampleDao;
import net.edu.module.entity.ProblemCodeSampleEntity;
import net.edu.module.query.ProblemCodeSampleQuery;
import net.edu.module.service.ProblemCodeSampleService;
import net.edu.module.vo.CodeProblemVO;
import net.edu.module.vo.ProblemCodeSampleVO;
import net.edu.module.vo.SampleVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 测试样例表
 *
 * @author sqw 
 * @since 1.0.0 2022-09-07
 */
@Service
@AllArgsConstructor
public class ProblemCodeSampleServiceImpl extends BaseServiceImpl<ProblemCodeSampleDao, ProblemCodeSampleEntity> implements ProblemCodeSampleService {

    private final ProblemCodeSampleDao problemCodeSampleDao;
    private final CodeProblemDao codeProblemDao;

    @Override
    public PageResult<ProblemCodeSampleVO> page(ProblemCodeSampleQuery query) {
        IPage<ProblemCodeSampleEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(ProblemCodeSampleConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private LambdaQueryWrapper<ProblemCodeSampleEntity> getWrapper(ProblemCodeSampleQuery query){
        LambdaQueryWrapper<ProblemCodeSampleEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(ProblemCodeSampleVO vo) {
        ProblemCodeSampleEntity entity = ProblemCodeSampleConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(ProblemCodeSampleVO vo) {
        ProblemCodeSampleEntity entity = ProblemCodeSampleConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
        ProblemCodeSampleVO vo = problemCodeSampleDao.selectSample(idList.get(0));
        codeProblemDao.updateSampleNum(vo.getProblemId());
    }

    @Override
    public CodeProblemVO getProblem(Long id) {
        return problemCodeSampleDao.selectProblem(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSample(List<SampleVO> sampleVOS,Long problemId) {
        sampleVOS.forEach((item)->{
            baseMapper.insert(ProblemCodeSampleConvert.INSTANCE.convert(item));
        });
        codeProblemDao.updateSampleNum(problemId);

    }


}