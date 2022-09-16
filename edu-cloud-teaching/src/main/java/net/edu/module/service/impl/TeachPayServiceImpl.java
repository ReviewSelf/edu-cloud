package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.TeachPayConvert;
import net.edu.module.entity.TeachPayEntity;
import net.edu.module.query.TeachPayQuery;
import net.edu.module.vo.TeachPayVO;
import net.edu.module.dao.TeachPayDao;
import net.edu.module.service.TeachPayService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 流水账管理
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-09-16
 */
@Service
@AllArgsConstructor
public class TeachPayServiceImpl extends BaseServiceImpl<TeachPayDao, TeachPayEntity> implements TeachPayService {

    @Override
    public PageResult<TeachPayVO> page(TeachPayQuery query) {
        IPage<TeachPayEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(TeachPayConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private LambdaQueryWrapper<TeachPayEntity> getWrapper(TeachPayQuery query){
        LambdaQueryWrapper<TeachPayEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(TeachPayVO vo) {
        TeachPayEntity entity = TeachPayConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(TeachPayVO vo) {
        TeachPayEntity entity = TeachPayConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

}