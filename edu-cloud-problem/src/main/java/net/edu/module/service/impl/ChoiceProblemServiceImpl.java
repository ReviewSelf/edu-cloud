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
import net.edu.module.vo.ChoiceOptionVO;
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

    private final ChoiceProblemDao choiceProblemDao;

    private final RedisUtils redisUtils;

    @Override
    public PageResult<ChoiceProblemVO> page(ChoiceProblemQuery query) {
        Page<ChoiceProblemVO> page = new Page<>(query.getPage(), query.getLimit());
        IPage<ChoiceProblemVO> list = choiceProblemDao.page(page, query);
        return new PageResult<>(list.getRecords(), list.getTotal());
    }


    @Override
    public ChoiceProblemVO getChoiceProblem(Long problemId) {
        return choiceProblemDao.selectChoiceProblem(problemId);
    }

    @Override
    @Transactional
    public void save(ChoiceProblemVO vo) {
        ChoiceProblemEntity entity = ChoiceProblemConvert.INSTANCE.convert(vo);
        entity.setOptionNum(vo.getOptions().size());
        baseMapper.insert(entity);
        if (vo.getOptions().size() > 0) {
            choiceProblemDao.insertOption(vo.getOptions(), entity.getId());
        }
        System.out.println(entity.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ChoiceProblemVO vo) {
        ChoiceProblemEntity entity = ChoiceProblemConvert.INSTANCE.convert(vo);
        entity.setOptionNum(vo.getOptions().size());
        //删除原先选项
        choiceProblemDao.deleteOption(entity.getId());
        redisUtils.del(RedisKeys.getChoiceOptions(vo.getId()));
        if (vo.getOptions().size() > 0) {
            choiceProblemDao.insertOption(vo.getOptions(), entity.getId());
        }
        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    public void updateStatus(Long problemId) {
        choiceProblemDao.updateStatus(problemId);
    }

    @Override
    public void updateUsedNum(Long id) {
        choiceProblemDao.updateUsedNum(id);
    }

    @Override
    public void updateSubmitTimes(Long id, Boolean isTrue) {
        choiceProblemDao.updateSubmitTimes(id, isTrue);
    }

    @Override
    public List<String> getChoiceOptions(Long problemId, int flag) {
        List<String> arr=null;
        if(flag==1){
            arr= (List<String>) redisUtils.get(RedisKeys.getChoiceOptions(problemId),RedisUtils.HOUR_ONE_EXPIRE);
            if(arr==null){
                arr=choiceProblemDao.selectChoiceOptions(problemId,flag);
                redisUtils.set(RedisKeys.getChoiceOptions(problemId),arr,RedisUtils.HOUR_ONE_EXPIRE);
            }
        }
        return arr;
    }

    @Override
    public List<ChoiceProblemVO> selectChoiceProblemInfo(List<Long> idList) {
        System.out.println(idList);
        return choiceProblemDao.selectChoiceProblemInfo(idList);
    }
}