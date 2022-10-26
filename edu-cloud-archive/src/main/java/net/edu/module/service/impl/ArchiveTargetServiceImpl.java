package net.edu.module.service.impl;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.ArchiveTargetConvert;
import net.edu.module.entity.ArchiveTargetEntity;
import net.edu.module.query.ArchiveTargetQuery;
import net.edu.module.vo.ArchiveTargetVO;
import net.edu.module.dao.ArchiveTargetDao;
import net.edu.module.service.ArchiveTargetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * target
 *
 * @author qxd babamu@126.com
 * @since 1.0.0 2022-10-24
 */
@Service
@AllArgsConstructor
public class ArchiveTargetServiceImpl extends BaseServiceImpl<ArchiveTargetDao, ArchiveTargetEntity> implements ArchiveTargetService {

    @Override
    public PageResult<ArchiveTargetVO> page(ArchiveTargetQuery query) {
        Page<ArchiveTargetVO> page = new Page<>(query.getPage(),query.getLimit());
        IPage<ArchiveTargetVO> list = baseMapper.page(page,query);
        return new PageResult<>(list.getRecords(), list.getTotal());
    }

    private LambdaQueryWrapper<ArchiveTargetEntity> getWrapper(ArchiveTargetQuery query){
        LambdaQueryWrapper<ArchiveTargetEntity> wrapper = Wrappers.lambdaQuery();
        return wrapper;
    }

    @Override
    public void save(ArchiveTargetVO vo) {
        ArchiveTargetEntity entity = ArchiveTargetConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(ArchiveTargetVO vo) {
        ArchiveTargetEntity entity = ArchiveTargetConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

}
