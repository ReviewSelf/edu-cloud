package net.edu.module.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.ChoiceProblemConvert;
import net.edu.module.entity.ChoiceProblemEntity;
import net.edu.module.entity.CodeProblemEntity;
import net.edu.module.entity.FillProblemEntity;
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

    @Override
    public PageResult<ChoiceProblemVO> page(ChoiceProblemQuery query) {
        IPage<ChoiceProblemEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(ChoiceProblemConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private LambdaQueryWrapper<ChoiceProblemEntity> getWrapper(ChoiceProblemQuery query){
        LambdaQueryWrapper<ChoiceProblemEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.like(StrUtil.isNotBlank(query.getName()), ChoiceProblemEntity::getName, query.getName());
        wrapper.eq(query.getType() != null, ChoiceProblemEntity::getType, query.getType());
        wrapper.eq(query.getStatus() != null, ChoiceProblemEntity::getStatus, query.getStatus());
        wrapper.eq(query.getDifficulty() != null, ChoiceProblemEntity::getDifficulty, query.getDifficulty());
        wrapper.orderByAsc(ChoiceProblemEntity::getUpdateTime);
        return wrapper;
    }

    @Override
    public void save(ChoiceProblemVO vo) {
        ChoiceProblemEntity entity = ChoiceProblemConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(ChoiceProblemVO vo) {
        ChoiceProblemEntity entity = ChoiceProblemConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

}