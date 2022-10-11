package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;

import net.edu.framework.common.cache.RedisKeys;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.RedisUtils;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.ChoiceProblemConvert;
import net.edu.module.entity.ChoiceProblemEntity;
import net.edu.module.query.ChoiceProblemQuery;
import net.edu.module.vo.ChoiceProblemVO;
import net.edu.module.dao.ChoiceProblemDao;
import net.edu.module.service.ChoiceProblemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 选择题库表
 *
 * @author 马佳浩
 * @since 1.0.0 2022-09-05
 */
@Service
@AllArgsConstructor
public class ChoiceProblemServiceImpl extends BaseServiceImpl<ChoiceProblemDao, ChoiceProblemEntity> implements ChoiceProblemService {

 

    private final RedisUtils redisUtils;

    @Override
    public PageResult<ChoiceProblemVO> page(ChoiceProblemQuery query) {
        Page<ChoiceProblemVO> page = new Page<>(query.getPage(), query.getLimit());
        IPage<ChoiceProblemVO> list = baseMapper.page(page, query);
        return new PageResult<>(list.getRecords(), list.getTotal());
    }


    @Override
    public ChoiceProblemVO getChoiceProblem(Long problemId) {
        return baseMapper.selectChoiceProblem(problemId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ChoiceProblemVO vo) {
        ChoiceProblemEntity entity = ChoiceProblemConvert.INSTANCE.convert(vo);
        entity.setOptionNum(vo.getOptions().size());
        baseMapper.insert(entity);
        if (vo.getOptions().size() > 0) {
            baseMapper.insertOption(vo.getOptions(), entity.getId());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ChoiceProblemVO vo) {
        ChoiceProblemEntity entity = ChoiceProblemConvert.INSTANCE.convert(vo);
        entity.setOptionNum(vo.getOptions().size());
        //删除原先选项
        baseMapper.deleteOption(entity.getId());
        if (vo.getOptions().size() > 0) {
            baseMapper.insertOption(vo.getOptions(), entity.getId());
        }
        updateById(entity);
        redisUtils.del(RedisKeys.getChoiceOptions(vo.getId()));
        redisUtils.del();RedisKeys.getProblemInfo(vo.getId(),"choice");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    public void updateStatus(Long problemId) {
        baseMapper.updateStatus(problemId);
    }

    @Override
    public void updateUsedNum(Long id) {
        baseMapper.updateUsedNum(id);
    }

    @Override
    public void updateSubmitTimes(Long id, Boolean isTrue) {
        baseMapper.updateSubmitTimes(id, isTrue);
    }



    /**
     * 获取选择题答案，判题时启用
     * @param problemId
     * @param flag
     * @return
     */
    @Override
    public List<String> getChoiceOptions(Long problemId, int flag) {
        List<String> arr=null;
        if(flag==1){
            arr= (List<String>) redisUtils.get(RedisKeys.getChoiceOptions(problemId));
            if(arr==null){
                arr=baseMapper.selectChoiceOptions(problemId,flag);
                redisUtils.set(RedisKeys.getChoiceOptions(problemId),arr,RedisUtils.HOUR_ONE_EXPIRE);
            }
        }
        return arr;
    }


    //答题显示内容，每次缓存10分钟，10分钟一更新提交次数
    @Override
    public ChoiceProblemVO getChoiceProblemInfo(Long problemId) {
        ChoiceProblemVO choiceProblemVO= (ChoiceProblemVO) redisUtils.get(RedisKeys.getProblemInfo(problemId,"choice"));
        if(choiceProblemVO==null){
            choiceProblemVO=baseMapper.selectChoiceProblemInfo(problemId);
            redisUtils.set(RedisKeys.getProblemInfo(problemId,"choice"),choiceProblemVO,RedisUtils.MIN_TEN_EXPIRE);
        }
        return choiceProblemVO;
    }

}