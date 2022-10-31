package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.AbilityConvert;
import net.edu.module.dao.AbilityDao;
import net.edu.module.entity.AbilityEntity;
import net.edu.module.query.AbilityQuery;
import net.edu.module.service.AbilityService;
import net.edu.module.vo.AbilityVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 能力纬度图
 *
 * @author sqw 
 * @since 1.0.0 2022-10-27
 */
@Service
@AllArgsConstructor
public class AbilityServiceImpl extends BaseServiceImpl<AbilityDao, AbilityEntity> implements AbilityService {

    @Override
    public PageResult<AbilityVO> page(AbilityQuery query) {
        IPage<AbilityEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(AbilityConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private LambdaQueryWrapper<AbilityEntity> getWrapper(AbilityQuery query){
        LambdaQueryWrapper<AbilityEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(AbilityVO vo) {
        AbilityEntity entity = AbilityConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(AbilityVO vo) {
        AbilityEntity entity = AbilityConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

}