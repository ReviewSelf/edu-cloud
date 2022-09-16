package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.TeachClassConvert;
import net.edu.module.dao.TeachClassDao;
import net.edu.module.dao.TeachPlanDao;
import net.edu.module.entity.TeachClassEntity;
import net.edu.module.query.TeachClassQuery;
import net.edu.module.query.TeachPlanQuery;
import net.edu.module.vo.TeachClassVO;
import net.edu.module.service.TeachClassService;
import net.edu.module.vo.TeachPlanVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 班级表
 *
 * @author wengruichen babamu@126.com
 * @since 1.0.0 2022-09-09
 */
@Service
@AllArgsConstructor
public class TeachClassServiceImpl extends BaseServiceImpl<TeachClassDao, TeachClassEntity> implements TeachClassService {

    private final TeachClassDao teachClassDao;



    @Override
    public PageResult<TeachClassVO> page(TeachClassQuery query) {
        Page<TeachClassVO> page = new Page<>(query.getPage(), query.getLimit());
        IPage<TeachClassVO> list = teachClassDao.page(page, query);
        return new PageResult<>(list.getRecords(), list.getTotal());
    }

    private LambdaQueryWrapper<TeachClassEntity> getWrapper(TeachClassQuery query){
        LambdaQueryWrapper<TeachClassEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(TeachClassVO vo) {
        TeachClassEntity entity = TeachClassConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(TeachClassVO vo) {
        TeachClassEntity entity = TeachClassConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    public List<TeachClassEntity> getOldClassUser(Long userId) {
        return teachClassDao.getOldClassUser(userId);
    }

}
