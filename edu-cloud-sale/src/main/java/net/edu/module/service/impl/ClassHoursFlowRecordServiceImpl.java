package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.ClassHoursFlowRecordConvert;
import net.edu.module.dao.ClassHoursFlowRecordDao;
import net.edu.module.entity.ClassHoursFlowRecordEntity;
import net.edu.framework.common.page.PageResult;
import net.edu.module.query.ClassHoursFlowRecordQuery;
import net.edu.module.service.ClassHoursFlowRecordService;
import net.edu.module.vo.ClassHoursFlowRecordVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 课时流水表
 *
 * @author sqw 
 * @since 1.0.0 2023-02-15
 */
@Service
@AllArgsConstructor
public class ClassHoursFlowRecordServiceImpl extends BaseServiceImpl<ClassHoursFlowRecordDao, ClassHoursFlowRecordEntity> implements ClassHoursFlowRecordService {

    @Override
    public PageResult<ClassHoursFlowRecordVO> page(ClassHoursFlowRecordQuery query) {
        IPage<ClassHoursFlowRecordEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(ClassHoursFlowRecordConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private LambdaQueryWrapper<ClassHoursFlowRecordEntity> getWrapper(ClassHoursFlowRecordQuery query){
        LambdaQueryWrapper<ClassHoursFlowRecordEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(ClassHoursFlowRecordVO vo) {
        ClassHoursFlowRecordEntity entity = ClassHoursFlowRecordConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(ClassHoursFlowRecordVO vo) {
        ClassHoursFlowRecordEntity entity = ClassHoursFlowRecordConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

}