package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.TeachCommunicateRecordConvert;
import net.edu.module.dao.TeachCommunicateRecordDao;
import net.edu.module.entity.TeachCommunicateRecordEntity;
import net.edu.module.query.TeachCommunicateRecordQuery;
import net.edu.module.service.TeachCommunicateRecordService;
import net.edu.module.vo.TeachCommunicateRecordVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 沟通记录表
 *
 * @author sqw 
 * @since 1.0.0 2023-02-05
 */
@Service
@AllArgsConstructor
public class TeachCommunicateRecordServiceImpl extends BaseServiceImpl<TeachCommunicateRecordDao, TeachCommunicateRecordEntity> implements TeachCommunicateRecordService {

    @Override
    public PageResult<TeachCommunicateRecordVO> page(TeachCommunicateRecordQuery query) {
        IPage<TeachCommunicateRecordEntity> page = baseMapper.selectCommunicateRecordPage(getPage(query), query);

        return new PageResult<>(TeachCommunicateRecordConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private LambdaQueryWrapper<TeachCommunicateRecordEntity> getWrapper(TeachCommunicateRecordQuery query){
        LambdaQueryWrapper<TeachCommunicateRecordEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(TeachCommunicateRecordVO vo) {
        TeachCommunicateRecordEntity entity = TeachCommunicateRecordConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(TeachCommunicateRecordVO vo) {
        TeachCommunicateRecordEntity entity = TeachCommunicateRecordConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

}