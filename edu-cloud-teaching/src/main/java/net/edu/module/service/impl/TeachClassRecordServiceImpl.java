package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.TeachClassRecordConvert;
import net.edu.module.dao.TeachClassRecordDao;
import net.edu.module.entity.TeachClassRecordEntity;
import net.edu.framework.common.page.PageResult;
import net.edu.module.query.TeachClassRecordQuery;
import net.edu.module.service.TeachClassRecordService;
import net.edu.module.vo.TeachClassRecordVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 学生班级记录
 *
 * @author sqw 
 * @since 1.0.0 2023-02-08
 */
@Service
@AllArgsConstructor
public class TeachClassRecordServiceImpl extends BaseServiceImpl<TeachClassRecordDao, TeachClassRecordEntity> implements TeachClassRecordService {

    @Override
    public PageResult<TeachClassRecordVO> page(TeachClassRecordQuery query) {
        IPage<TeachClassRecordEntity> page = baseMapper.selectClassRecordPage(getPage(query), query);
        return new PageResult<>(TeachClassRecordConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private LambdaQueryWrapper<TeachClassRecordEntity> getWrapper(TeachClassRecordQuery query){
        LambdaQueryWrapper<TeachClassRecordEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(TeachClassRecordVO vo) {
        TeachClassRecordEntity entity = TeachClassRecordConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(TeachClassRecordVO vo) {
        TeachClassRecordEntity entity = TeachClassRecordConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

}