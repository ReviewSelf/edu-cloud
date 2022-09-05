package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.FillProblemConvert;
import net.edu.module.entity.FillProblemEntity;
import net.edu.module.query.FillProblemQuery;
import net.edu.module.vo.FillProblemVO;
import net.edu.module.dao.FillProblemDao;
import net.edu.module.service.FillProblemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 填空题表
 *
 * @author 马佳浩 
 * @since 1.0.0 2022-09-05
 */
@Service
@AllArgsConstructor
public class FillProblemServiceImpl extends BaseServiceImpl<FillProblemDao, FillProblemEntity> implements FillProblemService {

    @Override
    public PageResult<FillProblemVO> page(FillProblemQuery query) {
        IPage<FillProblemEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(FillProblemConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private LambdaQueryWrapper<FillProblemEntity> getWrapper(FillProblemQuery query){
        LambdaQueryWrapper<FillProblemEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(FillProblemVO vo) {
        FillProblemEntity entity = FillProblemConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(FillProblemVO vo) {
        FillProblemEntity entity = FillProblemConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

}