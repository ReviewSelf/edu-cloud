package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.AllArgsConstructor;
import net.edu.module.dao.ArchiveWeightAssessTestDao;
import net.edu.module.entity.ArchiveWeightAssessTestEntity;
import net.edu.module.service.ArchiveWeightAssessTestService;
import net.edu.module.vo.ArchiveWeightAssessTestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author weng
 * @date 2022/11/3 - 11:40
 **/
@Service
@AllArgsConstructor
public class ArchiveWeightAssessTestServiceImpl implements ArchiveWeightAssessTestService {

    @Autowired
    private ArchiveWeightAssessTestDao archiveWeightAssessTestDao;
    @Override
    public Integer insertTestWeight(List<ArchiveWeightAssessTestVO> VOS) {
        return archiveWeightAssessTestDao.insertTestWeight(VOS);
    }

    @Override
    public boolean saveBatch(Collection<ArchiveWeightAssessTestEntity> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<ArchiveWeightAssessTestEntity> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<ArchiveWeightAssessTestEntity> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(ArchiveWeightAssessTestEntity entity) {
        return false;
    }

    @Override
    public ArchiveWeightAssessTestEntity getOne(Wrapper<ArchiveWeightAssessTestEntity> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<ArchiveWeightAssessTestEntity> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<ArchiveWeightAssessTestEntity> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<ArchiveWeightAssessTestEntity> getBaseMapper() {
        return null;
    }

    @Override
    public Class<ArchiveWeightAssessTestEntity> getEntityClass() {
        return null;
    }
}