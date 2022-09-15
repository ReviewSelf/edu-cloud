package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.LessonProblemConvert;
import net.edu.module.entity.LessonProblemEntity;
import net.edu.module.query.LessonProblemQuery;
import net.edu.module.vo.LessonProblemVO;
import net.edu.module.dao.LessonProblemDao;
import net.edu.module.service.LessonProblemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 课堂练习表
 *
 * @author 马佳浩 
 * @since 1.0.0 2022-09-15
 */
@Service
@AllArgsConstructor
public class LessonProblemServiceImpl extends BaseServiceImpl<LessonProblemDao, LessonProblemEntity> implements LessonProblemService {

    @Override
    public PageResult<LessonProblemVO> page(LessonProblemQuery query) {
        IPage<LessonProblemEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(LessonProblemConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private LambdaQueryWrapper<LessonProblemEntity> getWrapper(LessonProblemQuery query){
        LambdaQueryWrapper<LessonProblemEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(LessonProblemVO vo) {
        LessonProblemEntity entity = LessonProblemConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(LessonProblemVO vo) {
        LessonProblemEntity entity = LessonProblemConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

}