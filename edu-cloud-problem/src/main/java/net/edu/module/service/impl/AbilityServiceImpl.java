package net.edu.module.service.impl;

import lombok.AllArgsConstructor;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.AbilityConvert;
import net.edu.module.dao.AbilityDao;
import net.edu.module.entity.AbilityEntity;
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
    public List<AbilityEntity> list() {
        List<AbilityEntity> list = baseMapper.selectList(null);

        return list;
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