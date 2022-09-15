package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.LessonAttendLogConvert;
import net.edu.module.dao.LessonAttendLogDao;
import net.edu.module.entity.LessonAttendLogEntity;
import net.edu.module.query.LessonAttendLogQuery;
import net.edu.module.service.LessonAttendLogService;
import net.edu.module.vo.LessonAttendLogVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 课堂签到表
 *
 * @author 马佳浩 
 * @since 1.0.0 2022-09-15
 */
@Service
@AllArgsConstructor
public class LessonAttendLogServiceImpl extends BaseServiceImpl<LessonAttendLogDao, LessonAttendLogEntity> implements LessonAttendLogService {

    @Override
    public PageResult<LessonAttendLogVO> page(LessonAttendLogQuery query) {
        IPage<LessonAttendLogEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(LessonAttendLogConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private LambdaQueryWrapper<LessonAttendLogEntity> getWrapper(LessonAttendLogQuery query){
        LambdaQueryWrapper<LessonAttendLogEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(LessonAttendLogVO vo) {
        LessonAttendLogEntity entity = LessonAttendLogConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(LessonAttendLogVO vo) {
        LessonAttendLogEntity entity = LessonAttendLogConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

}