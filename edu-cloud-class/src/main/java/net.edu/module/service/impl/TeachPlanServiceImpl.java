package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.TeachPlanConvert;
import net.edu.module.dao.TeachPlanDao;
import net.edu.module.entity.TeachPlanEntity;
import net.edu.module.query.TeachPlanQuery;
import net.edu.module.service.TeachPlanService;
import net.edu.module.vo.TeachPlanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 教学计划表
 *
 * @author 阿沐
 * @since 1.0.0 2022-09-08
 */
@Service
@AllArgsConstructor
public class TeachPlanServiceImpl extends BaseServiceImpl<TeachPlanDao, TeachPlanEntity> implements TeachPlanService {

    @Autowired
    private TeachPlanDao teachPlanDao;



    @Override
    public PageResult<TeachPlanVO> page(TeachPlanQuery query) {

        Page<TeachPlanVO> page = new Page<>(query.getPage(), query.getLimit());
        IPage<TeachPlanVO> list =teachPlanDao.getTeachPlanByPage(page,query);
        return new PageResult<>(list.getRecords(), page.getTotal());
    }

    private LambdaQueryWrapper<TeachPlanEntity> getWrapper(TeachPlanQuery query){
        LambdaQueryWrapper<TeachPlanEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(TeachPlanVO vo) {
        TeachPlanEntity entity = TeachPlanConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(TeachPlanVO vo) {
        TeachPlanEntity entity = TeachPlanConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

}
