package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.ArchiveWeightTargetAssessConvert;
import net.edu.module.entity.ArchiveWeightTargetAssessEntity;
import net.edu.module.query.ArchiveWeightTargetAssessQuery;
import net.edu.module.vo.ArchiveWeightTargetAssessVO;
import net.edu.module.dao.ArchiveWeightTargetAssessDao;
import net.edu.module.service.ArchiveWeightTargetAssessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 考核点权重
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-29
 */
@Service
@AllArgsConstructor
public class ArchiveWeightTargetAssessServiceImpl extends BaseServiceImpl<ArchiveWeightTargetAssessDao, ArchiveWeightTargetAssessEntity> implements ArchiveWeightTargetAssessService {

    @Autowired
    private ArchiveWeightTargetAssessDao archiveWeightTargetAssessDao;
    @Override
    public PageResult<ArchiveWeightTargetAssessVO> page(ArchiveWeightTargetAssessQuery query) {
        IPage<ArchiveWeightTargetAssessEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(ArchiveWeightTargetAssessConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private LambdaQueryWrapper<ArchiveWeightTargetAssessEntity> getWrapper(ArchiveWeightTargetAssessQuery query){
        LambdaQueryWrapper<ArchiveWeightTargetAssessEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(ArchiveWeightTargetAssessVO vo) {
        ArchiveWeightTargetAssessEntity entity = ArchiveWeightTargetAssessConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(ArchiveWeightTargetAssessVO vo) {
        ArchiveWeightTargetAssessEntity entity = ArchiveWeightTargetAssessConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    public Integer deleteByAssessId(Long id) {
        return archiveWeightTargetAssessDao.deleteByAssessId(id);
    }

    @Override
    public List<ArchiveWeightTargetAssessVO> selectAssessByCourseId(Long courseId) {
        return archiveWeightTargetAssessDao.selectAssessByCourseId(courseId);
    }

    @Override
    public Integer insertAssessWeight(List<ArchiveWeightTargetAssessVO> assessVOS) {
        return archiveWeightTargetAssessDao.insertAssessWeight(assessVOS);
    }

    @Override
    public Integer insertArchiveAccess2(ArchiveWeightTargetAssessVO vo) {
        return archiveWeightTargetAssessDao.insertArchiveAccess2(vo);
    }

}
