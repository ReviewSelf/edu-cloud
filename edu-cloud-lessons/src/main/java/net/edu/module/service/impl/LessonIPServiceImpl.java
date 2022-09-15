package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.LessonIPConvert;
import net.edu.module.dao.LessonIPDao;
import net.edu.module.entity.LessonIPEntity;
import net.edu.module.query.LessonIPQuery;
import net.edu.module.service.LessonIPService;
import net.edu.module.vo.LessonIPVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 1
 *
 * @author 马佳浩 
 * @since 1.0.0 2022-09-15
 */
@Service
@AllArgsConstructor
public class LessonIPServiceImpl extends BaseServiceImpl<LessonIPDao, LessonIPEntity> implements LessonIPService {

    @Override
    public PageResult<LessonIPVO> page(LessonIPQuery query) {
        IPage<LessonIPEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(LessonIPConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private LambdaQueryWrapper<LessonIPEntity> getWrapper(LessonIPQuery query){
        LambdaQueryWrapper<LessonIPEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(LessonIPVO vo) {
        LessonIPEntity entity = LessonIPConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(LessonIPVO vo) {
        LessonIPEntity entity = LessonIPConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

}