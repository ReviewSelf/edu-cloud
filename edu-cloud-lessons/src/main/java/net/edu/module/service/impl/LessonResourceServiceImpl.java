package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.LessonResourceConvert;
import net.edu.module.entity.LessonResourceEntity;
import net.edu.module.query.LessonResourceQuery;
import net.edu.module.vo.LessonResourceVO;
import net.edu.module.dao.LessonResourceDao;
import net.edu.module.service.LessonResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 教学日历资源表
 *
 * @author 马佳浩 babamu@126.com
 * @since 1.0.0 2022-09-15
 */
@Service
@AllArgsConstructor
public class LessonResourceServiceImpl extends BaseServiceImpl<LessonResourceDao, LessonResourceEntity> implements LessonResourceService {

    private final EduTeachApi eduTeachApi;
    private final LessonResourceDao lessonResourceDao;
    private final LessonResourceDao lessonResourceDao;

    @Override
    public PageResult<LessonResourceVO> page(LessonResourceQuery query) {
        IPage<LessonResourceEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(LessonResourceConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private LambdaQueryWrapper<LessonResourceEntity> getWrapper(LessonResourceQuery query){
        LambdaQueryWrapper<LessonResourceEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(LessonResourceVO vo) {
        LessonResourceEntity entity = LessonResourceConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(LessonResourceVO vo) {
        LessonResourceEntity entity = LessonResourceConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    public void copyFromPlanItem(Long planItemId,Long lessonId) {
        //根据id获取资源列表
        List<TeachPlanItemResourceVO> list =eduTeachApi.getItemResource(planItemId).getData();
        if(!CollectionUtil.isEmpty(list)){
            // 插入至数据库
            lessonResourceDao.insertResourceList(list,lessonId);
        }

    }

    @Override
    public List<LessonResourceVO> getLessonResource(Long lessonId) {
        return lessonResourceDao.selectLessonResource(lessonId);
    }
}