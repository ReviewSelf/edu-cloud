package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import net.edu.framework.common.constant.Constant;
import net.edu.framework.common.exception.ServerException;
import net.edu.framework.common.utils.TreeUtils;
import net.edu.framework.common.utils.YouBianCodeUtil;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.AbilityDimConvert;
import net.edu.module.entity.AbilityDimEntity;
import net.edu.module.entity.KnowledgePointEntity;
import net.edu.module.query.AbilityDimQuery;
import net.edu.module.vo.AbilityDimVO;
import net.edu.module.dao.AbilityDimDao;
import net.edu.module.service.AbilityDimService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 能力模块表
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-25
 */
@Service
@AllArgsConstructor
public class AbilityDimServiceImpl extends BaseServiceImpl<AbilityDimDao, AbilityDimEntity> implements AbilityDimService {

    private final AbilityDimDao abilityDimDao;

    @Override
    public List<AbilityDimVO> getAdList() {
        List<AbilityDimEntity> list= abilityDimDao.getAdList();
        return TreeUtils.build(AbilityDimConvert.INSTANCE.convertList(list), Constant.ROOT);
    }

    private LambdaQueryWrapper<AbilityDimEntity> getWrapper(AbilityDimQuery query){
        LambdaQueryWrapper<AbilityDimEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(AbilityDimVO vo) {
        AbilityDimEntity entity = AbilityDimConvert.INSTANCE.convert(vo);
        //确定层级
        AbilityDimEntity bEntity= baseMapper.selectBrotherEntity(vo.getPid());
        if (bEntity==null) {
            if (0==vo.getPid()) {
                //情况1
                entity.setLevel(1);
            } else {
                //情况2
                AbilityDimEntity parent =  baseMapper.selectById(vo.getPid());
                entity.setLevel(parent.getLevel()+1);
            }
        }else {
            //情况三
            entity.setLevel(bEntity.getLevel());
        }
        baseMapper.insert(entity);
    }

    @Override
    public void update(AbilityDimVO vo) {
        AbilityDimEntity entity = AbilityDimConvert.INSTANCE.convert(vo);
        // 上级知识点不能为自己
        if(entity.getId().equals(entity.getPid())){
            throw new ServerException("上级知识点不能为自己");
        }
        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

}