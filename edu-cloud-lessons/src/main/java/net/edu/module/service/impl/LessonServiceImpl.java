package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.LessonConvert;
import net.edu.module.entity.LessonEntity;
import net.edu.module.query.LessonQuery;
import net.edu.module.vo.LessonVO;
import net.edu.module.dao.LessonDao;
import net.edu.module.service.LessonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 课程表
 *
 * @author 马佳浩 
 * @since 1.0.0 2022-09-15
 */
@Service
@AllArgsConstructor
public class LessonServiceImpl extends BaseServiceImpl<LessonDao, LessonEntity> implements LessonService {

    @Override
    public List<LessonVO> list(LessonQuery query) {
        LambdaQueryWrapper<LessonEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(true, LessonEntity::getClassId, query.getClassId());
        List<LessonEntity> list = baseMapper.selectList(wrapper);
        return LessonConvert.INSTANCE.convertList(list);
    }

    @Override
    public void save(LessonVO vo) {
        LessonEntity entity = LessonConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(LessonVO vo) {
        LessonEntity entity = LessonConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

}