package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.ArchiveScoreBookConvert;
import net.edu.module.dao.ArchiveScoreBookDao;
import net.edu.module.query.ArchiveScoreBookQuery;
import net.edu.module.service.ArchiveScoreBookService;
import net.edu.module.vo.ArchiveScoreBookVO;
import net.maku.entity.ArchiveScoreBookEntity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 记分册
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-11-22
 */
@Service
@AllArgsConstructor
public class ArchiveScoreBookServiceImpl extends BaseServiceImpl<ArchiveScoreBookDao, ArchiveScoreBookEntity> implements ArchiveScoreBookService {

    @Override
    public PageResult<ArchiveScoreBookVO> page(ArchiveScoreBookQuery query) {
        IPage<ArchiveScoreBookEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(ArchiveScoreBookConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private LambdaQueryWrapper<ArchiveScoreBookEntity> getWrapper(ArchiveScoreBookQuery query){
        LambdaQueryWrapper<ArchiveScoreBookEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(ArchiveScoreBookVO vo) {
        ArchiveScoreBookEntity entity = ArchiveScoreBookConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(ArchiveScoreBookVO vo) {
        ArchiveScoreBookEntity entity = ArchiveScoreBookConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

}
