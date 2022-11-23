package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.ArchiveCourseSummaryConvert;
import net.edu.module.entity.ArchiveCourseSummaryEntity;
import net.edu.module.query.ArchiveCourseSummaryQuery;
import net.edu.module.vo.ArchiveCourseSummaryVO;
import net.edu.module.dao.ArchiveCourseSummaryDao;
import net.edu.module.service.ArchiveCourseSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 课程总结
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-11-14
 */
@Service
@AllArgsConstructor
public class ArchiveCourseSummaryServiceImpl extends BaseServiceImpl<ArchiveCourseSummaryDao, ArchiveCourseSummaryEntity> implements ArchiveCourseSummaryService {

    @Autowired
    ArchiveCourseSummaryDao archiveCourseSummaryDao;

    @Override
    public PageResult<ArchiveCourseSummaryVO> page(ArchiveCourseSummaryQuery query) {
        IPage<ArchiveCourseSummaryEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(ArchiveCourseSummaryConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private LambdaQueryWrapper<ArchiveCourseSummaryEntity> getWrapper(ArchiveCourseSummaryQuery query){
        LambdaQueryWrapper<ArchiveCourseSummaryEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(ArchiveCourseSummaryVO vo) {
        ArchiveCourseSummaryEntity entity = ArchiveCourseSummaryConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(ArchiveCourseSummaryVO vo) {
        ArchiveCourseSummaryEntity entity = ArchiveCourseSummaryConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }


}