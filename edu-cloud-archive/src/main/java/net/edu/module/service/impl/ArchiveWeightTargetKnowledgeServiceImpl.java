package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.ArchiveWeightTargetKnowledgeConvert;
import net.edu.module.entity.ArchiveWeightTargetKnowledgeEntity;
import net.edu.module.query.ArchiveWeightTargetKnowledgeQuery;
import net.edu.module.vo.ArchiveWeightTargetKnowledgeVO;
import net.edu.module.dao.ArchiveWeightTargetKnowledgeDao;
import net.edu.module.service.ArchiveWeightTargetKnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 一级知识点权重
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-29
 */
@Service
@AllArgsConstructor
public class ArchiveWeightTargetKnowledgeServiceImpl extends BaseServiceImpl<ArchiveWeightTargetKnowledgeDao, ArchiveWeightTargetKnowledgeEntity> implements ArchiveWeightTargetKnowledgeService {

    @Autowired
    private ArchiveWeightTargetKnowledgeDao archiveWeightTargetKnowledgeDao;


    @Override
    public PageResult<ArchiveWeightTargetKnowledgeVO> page(ArchiveWeightTargetKnowledgeQuery query) {
        IPage<ArchiveWeightTargetKnowledgeEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(ArchiveWeightTargetKnowledgeConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private LambdaQueryWrapper<ArchiveWeightTargetKnowledgeEntity> getWrapper(ArchiveWeightTargetKnowledgeQuery query){
        LambdaQueryWrapper<ArchiveWeightTargetKnowledgeEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(ArchiveWeightTargetKnowledgeVO vo) {
        ArchiveWeightTargetKnowledgeEntity entity = ArchiveWeightTargetKnowledgeConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(ArchiveWeightTargetKnowledgeVO vo) {
        ArchiveWeightTargetKnowledgeEntity entity = ArchiveWeightTargetKnowledgeConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    public List<ArchiveWeightTargetKnowledgeVO> selectKnowledgeByTargetId(Long targetId) {
        return archiveWeightTargetKnowledgeDao.selectKnowledgeByTargetId(targetId);
    }

    @Override
    public Integer insertKnowledgeWeight(List<ArchiveWeightTargetKnowledgeVO> archiveWeightTargetKnowledgeVO) {
        return archiveWeightTargetKnowledgeDao.insertKnowledgeWeight(archiveWeightTargetKnowledgeVO);
    }

}