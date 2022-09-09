package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.CodeSampleConvert;
import net.edu.module.dao.CodeProblemDao;
import net.edu.module.dao.CodeSampleDao;
import net.edu.module.entity.CodeProblemEntity;
import net.edu.module.entity.CodeSampleEntity;
import net.edu.module.query.CodeSampleQuery;
import net.edu.module.service.CodeSampleService;
import net.edu.module.vo.CodeSampleVO;
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
public class CodeSampleServiceImpl extends BaseServiceImpl<CodeSampleDao, CodeSampleEntity> implements CodeSampleService {

    private final CodeProblemDao codeProblemDao;

    @Override
    public PageResult<CodeSampleVO> page(CodeSampleQuery query) {
        IPage<CodeSampleEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(CodeSampleConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private LambdaQueryWrapper<CodeSampleEntity> getWrapper(CodeSampleQuery query) {
        LambdaQueryWrapper<CodeSampleEntity> wrapper = Wrappers.lambdaQuery();
        return wrapper;
    }


    @Override
    public void update(CodeSampleVO vo) {
        CodeSampleEntity entity = CodeSampleConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        CodeSampleEntity entity=getById(idList.get(0));
        removeByIds(idList);
        codeProblemDao.updateSampleNum(entity.getProblemId());
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSample(List<SampleVO> sampleVOS, Long problemId) {
        sampleVOS.forEach((item) -> {
            baseMapper.insert(CodeSampleConvert.INSTANCE.convert(item));
        });
        codeProblemDao.updateSampleNum(problemId);
    }


}