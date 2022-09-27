package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import net.edu.framework.common.cache.RedisKeys;
import net.edu.framework.common.utils.RedisUtils;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.api.vo.SampleVO;
import net.edu.module.convert.CodeSampleConvert;
import net.edu.module.dao.CodeProblemDao;
import net.edu.module.dao.CodeSampleDao;
import net.edu.module.entity.CodeSampleEntity;
import net.edu.module.service.CodeSampleService;
import net.edu.module.vo.CodeSampleVO;
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
    private final RedisUtils redisUtils;


    @Override
    public List<CodeSampleVO> getList(Long problemId) {

        List<CodeSampleVO> arr=(List<CodeSampleVO>) redisUtils.get(RedisKeys.getSample(problemId),RedisUtils.HOUR_ONE_EXPIRE);
        if(arr==null){
            LambdaQueryWrapper<CodeSampleEntity> wrapper = Wrappers.lambdaQuery();
            wrapper.eq(true, CodeSampleEntity::getProblemId, problemId);
            arr=CodeSampleConvert.INSTANCE.convertList(baseMapper.selectList(wrapper));
            redisUtils.set(RedisKeys.getSample(problemId),arr,RedisUtils.HOUR_ONE_EXPIRE);
        }
        return arr;
    }


    @Override
    public void update(CodeSampleVO vo) {
        CodeSampleEntity entity = CodeSampleConvert.INSTANCE.convert(vo);
        redisUtils.del(RedisKeys.getSample(vo.getProblemId()));
        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        CodeSampleEntity entity=getById(idList.get(0));
        removeByIds(idList);
        codeProblemDao.updateSampleNum(entity.getProblemId());
        redisUtils.del(RedisKeys.getSample(entity.getProblemId()));
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSample(List<SampleVO> sampleVos, Long problemId) {
        sampleVos.forEach((item) -> baseMapper.insert(CodeSampleConvert.INSTANCE.convert(item)));
        redisUtils.del(RedisKeys.getSample(problemId));
        codeProblemDao.updateSampleNum(problemId);
    }


}