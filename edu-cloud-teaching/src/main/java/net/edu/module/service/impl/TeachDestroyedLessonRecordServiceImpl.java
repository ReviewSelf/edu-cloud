package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.TeachDestroyedLessonRecordConvert;
import net.edu.module.dao.TeachDestroyedLessonRecordDao;
import net.edu.module.entity.TeachDestroyedLessonRecordEntity;
import net.edu.module.query.TeachDestroyedLessonRecordQuery;
import net.edu.module.service.TeachDestroyedLessonRecordService;
import net.edu.module.vo.TeachDestroyedLessonRecordVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 消课管理
 *
 * @author sqw 
 * @since 1.0.0 2023-03-04
 */
@Service
@AllArgsConstructor
public class TeachDestroyedLessonRecordServiceImpl extends BaseServiceImpl<TeachDestroyedLessonRecordDao, TeachDestroyedLessonRecordEntity> implements TeachDestroyedLessonRecordService {

    @Override
    public PageResult<TeachDestroyedLessonRecordVO> page(TeachDestroyedLessonRecordQuery query) {
        IPage<TeachDestroyedLessonRecordVO> page = baseMapper.page(getPage(query), query);

        return new PageResult<>(page.getRecords(), page.getTotal());
    }

    @Override
    public void save(TeachDestroyedLessonRecordVO vo) {
        TeachDestroyedLessonRecordEntity entity = TeachDestroyedLessonRecordConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(TeachDestroyedLessonRecordVO vo) {
        TeachDestroyedLessonRecordEntity entity = TeachDestroyedLessonRecordConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

}