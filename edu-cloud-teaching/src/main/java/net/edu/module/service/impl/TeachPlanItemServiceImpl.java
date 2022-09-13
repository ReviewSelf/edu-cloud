package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.TeachPlanItemConvert;
import net.edu.module.dao.TeachPlanItemDao;
import net.edu.module.entity.TeachPlanItemEntity;
import net.edu.module.query.TeachPlanItemQuery;
import net.edu.module.service.TeachPlanItemService;
import net.edu.module.vo.TeachPlanItemVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 教学日历表
 *
 * @author sqw 
 * @since 1.0.0 2022-09-12
 */
@Service
@AllArgsConstructor
public class TeachPlanItemServiceImpl extends BaseServiceImpl<TeachPlanItemDao, TeachPlanItemEntity> implements TeachPlanItemService {

    private final TeachPlanItemDao teachPlanItemDao;

    @Override
    public List<TeachPlanItemVO> page( Long id) {
        List<TeachPlanItemVO> list = teachPlanItemDao.page(id);
        return  list;
    }

    private LambdaQueryWrapper<TeachPlanItemEntity> getWrapper(TeachPlanItemQuery query){
        LambdaQueryWrapper<TeachPlanItemEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(TeachPlanItemVO vo) {
        TeachPlanItemEntity entity = TeachPlanItemConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(TeachPlanItemVO vo) {
        TeachPlanItemEntity entity = TeachPlanItemConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

}