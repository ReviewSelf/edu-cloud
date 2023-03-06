package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.TeachClassHoursFlowRecordConvert;
import net.edu.module.dao.TeachClassHoursFlowRecordDao;
import net.edu.module.entity.TeachClassHoursFlowRecordEntity;
import net.edu.module.query.TeachClassHoursFlowRecordQuery;
import net.edu.module.service.TeachClassHoursFlowRecordService;
import net.edu.module.vo.TeachClassHoursFlowRecordVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 课时流水管理
 *
 * @author sqw 
 * @since  2023-03-06
 */
@Service
@AllArgsConstructor
public class TeachClassHoursFlowRecordServiceImpl extends BaseServiceImpl<TeachClassHoursFlowRecordDao, TeachClassHoursFlowRecordEntity> implements TeachClassHoursFlowRecordService {

    @Override
    public PageResult<TeachClassHoursFlowRecordVO> page(TeachClassHoursFlowRecordQuery query) {
        IPage<TeachClassHoursFlowRecordVO> page = baseMapper.selectFlowRecordPage(getPage(query), query);

        return new PageResult<>(page.getRecords(), page.getTotal());
    }

    @Override
    public void save(TeachClassHoursFlowRecordVO vo) {
        TeachClassHoursFlowRecordEntity entity = TeachClassHoursFlowRecordConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(TeachClassHoursFlowRecordVO vo) {
        TeachClassHoursFlowRecordEntity entity = TeachClassHoursFlowRecordConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

}