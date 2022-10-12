package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.TeachEvaluateConvert;
import net.edu.module.entity.TeachEvaluateEntity;
import net.edu.module.query.TeachEvaluateQuery;
import net.edu.module.vo.TeachClassVO;
import net.edu.module.vo.TeachEvaluateVO;
import net.edu.module.dao.TeachEvaluateDao;
import net.edu.module.service.TeachEvaluateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 教学评价
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-08
 */
@Service
@AllArgsConstructor
public class TeachEvaluateServiceImpl extends BaseServiceImpl<TeachEvaluateDao, TeachEvaluateEntity> implements TeachEvaluateService {

    TeachEvaluateDao teachEvaluateDao;

    @Override
    public PageResult<TeachEvaluateVO> page(TeachEvaluateQuery query) {
        Page<TeachEvaluateVO> page = new Page<>(query.getPage(), query.getLimit());
        IPage<TeachEvaluateVO> list = teachEvaluateDao.page(page, query);
        return new PageResult<>(list.getRecords(), list.getTotal());
    }

    private LambdaQueryWrapper<TeachEvaluateEntity> getWrapper(TeachEvaluateQuery query){
        LambdaQueryWrapper<TeachEvaluateEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(TeachEvaluateVO vo) {
        TeachEvaluateEntity entity = TeachEvaluateConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(TeachEvaluateVO vo) {
        TeachEvaluateEntity entity = TeachEvaluateConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }


}