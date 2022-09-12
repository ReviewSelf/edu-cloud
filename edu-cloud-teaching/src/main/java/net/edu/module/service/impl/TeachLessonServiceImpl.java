package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.TeachLessonConvert;
import net.edu.module.entity.TeachLessonEntity;
import net.edu.module.query.TeachLessonQuery;
import net.edu.module.vo.TeachLessonVO;
import net.edu.module.dao.TeachLessonDao;
import net.edu.module.service.TeachLessonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 课程表
 *
 * @author 翁瑞辰 babamu@126.com
 * @since 1.0.0 2022-09-09
 */
@Service
@AllArgsConstructor
public class TeachLessonServiceImpl extends BaseServiceImpl<TeachLessonDao, TeachLessonEntity> implements TeachLessonService {

    @Override
    public PageResult<TeachLessonVO> page(TeachLessonQuery query) {
        IPage<TeachLessonEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(TeachLessonConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private LambdaQueryWrapper<TeachLessonEntity> getWrapper(TeachLessonQuery query){
        LambdaQueryWrapper<TeachLessonEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(TeachLessonVO vo) {
        TeachLessonEntity entity = TeachLessonConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(TeachLessonVO vo) {
        TeachLessonEntity entity = TeachLessonConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

}