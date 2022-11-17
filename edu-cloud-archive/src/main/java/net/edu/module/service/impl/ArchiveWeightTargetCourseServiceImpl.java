package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.ArchiveWeightTargetCourseConvert;
import net.edu.module.entity.ArchiveWeightTargetCourseEntity;
import net.edu.module.query.ArchiveWeightTargetCourseQuery;
import net.edu.module.vo.ArchiveWeightTargetCourseVO;
import net.edu.module.dao.ArchiveWeightTargetCourseDao;
import net.edu.module.service.ArchiveWeightTargetCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 一级知识点权重
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-29
 */
@Service
@AllArgsConstructor
public class ArchiveWeightTargetCourseServiceImpl extends BaseServiceImpl<ArchiveWeightTargetCourseDao, ArchiveWeightTargetCourseEntity> implements ArchiveWeightTargetCourseService {

    @Autowired
    private ArchiveWeightTargetCourseDao archiveWeightTargetCourseDao;


    @Override
    public PageResult<ArchiveWeightTargetCourseVO> page(ArchiveWeightTargetCourseQuery query) {
        IPage<ArchiveWeightTargetCourseEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(ArchiveWeightTargetCourseConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private LambdaQueryWrapper<ArchiveWeightTargetCourseEntity> getWrapper(ArchiveWeightTargetCourseQuery query){
        LambdaQueryWrapper<ArchiveWeightTargetCourseEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(ArchiveWeightTargetCourseVO vo) {
        ArchiveWeightTargetCourseEntity entity = ArchiveWeightTargetCourseConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(ArchiveWeightTargetCourseVO vo) {
        ArchiveWeightTargetCourseEntity entity = ArchiveWeightTargetCourseConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    public void delete(Long targetId,Long courseId) {
        archiveWeightTargetCourseDao.updateDeleted(targetId,courseId);
    }

    @Override
    public List<ArchiveWeightTargetCourseVO> selectCourseByTargetId(Long targetId) {
        return archiveWeightTargetCourseDao.selectCourseByTargetId(targetId);
    }

    @Override
    public Integer insertCourseWeight(List<ArchiveWeightTargetCourseVO> archiveWeightTargetCourseVO) {
        return archiveWeightTargetCourseDao.insertCourseWeight(archiveWeightTargetCourseVO);
    }

    @Override
    public ArchiveWeightTargetCourseVO selectById(Long id) {
        return archiveWeightTargetCourseDao.getById(id);
    }

    @Override
    public List<ArchiveWeightTargetCourseVO> selectCourseByCourseId(Long courseId) {
        return archiveWeightTargetCourseDao.selectCourseByCourseId(courseId);
    }


}
