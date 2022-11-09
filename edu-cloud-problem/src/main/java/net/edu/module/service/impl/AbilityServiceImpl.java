package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import lombok.AllArgsConstructor;
import net.edu.framework.common.constant.Constant;
import net.edu.framework.common.utils.TreeUtils;
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
    public List<AbilityVO> getAbilityList() {
        List<AbilityEntity> list=baseMapper.selectList(null);

        return TreeUtils.build(AbilityConvert.INSTANCE.convertList(list), Constant.ROOT);
    }

    @Override
    public List<AbilityVO> getAbilityItemList(Long id) {
        return  baseMapper.selectAbilityItemList(id);
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