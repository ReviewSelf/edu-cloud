package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.ArchiveCourseConvert;
import net.edu.module.entity.ArchiveCourseEntity;
import net.edu.module.query.ArchiveCourseQuery;
import net.edu.module.vo.ArchiveCourseVO;
import net.edu.module.dao.ArchiveCourseDao;
import net.edu.module.service.ArchiveCourseService;
import net.edu.module.vo.ArchiveTargetVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 能力课程
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-25
 */
@Service
@AllArgsConstructor
public class ArchiveCourseServiceImpl extends BaseServiceImpl<ArchiveCourseDao, ArchiveCourseEntity> implements ArchiveCourseService {

    @Autowired
    private ArchiveCourseDao archiveCourseDao;
    @Override
    public PageResult<ArchiveCourseVO> page(ArchiveCourseQuery query) {
        Page<ArchiveCourseVO> page = new Page<>(query.getPage(),query.getLimit());
        IPage<ArchiveCourseVO> list = archiveCourseDao.selectArchiveCourseByPage(page,query);

        return new PageResult<>(list.getRecords(),page.getTotal());
    }

    @Override
    public List<ArchiveCourseVO> getName() {
        List<ArchiveCourseVO> courseVOList=baseMapper.selectName();
        return courseVOList;
    }

    private LambdaQueryWrapper<ArchiveCourseEntity> getWrapper(ArchiveCourseQuery query){
        LambdaQueryWrapper<ArchiveCourseEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(ArchiveCourseVO vo) {
        ArchiveCourseEntity entity = ArchiveCourseConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(ArchiveCourseVO vo) {
        ArchiveCourseEntity entity = ArchiveCourseConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

}
