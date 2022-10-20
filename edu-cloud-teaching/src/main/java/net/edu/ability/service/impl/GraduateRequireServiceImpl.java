package net.edu.ability.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import net.edu.ability.convert.GraduateRequireConvert;
import net.edu.ability.dao.GraduateRequireDao;
import net.edu.ability.entity.GraduateRequireEntity;
import net.edu.ability.query.GraduateRequireQuery;
import net.edu.ability.service.GraduateRequireService;
import net.edu.ability.vo.GraduateRequireVO;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 毕业要求
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-20
 */
@Service
@AllArgsConstructor
public class GraduateRequireServiceImpl extends BaseServiceImpl<GraduateRequireDao, GraduateRequireEntity> implements GraduateRequireService {

    @Override
    public PageResult<GraduateRequireVO> page(GraduateRequireQuery query) {
        IPage<GraduateRequireEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(GraduateRequireConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private LambdaQueryWrapper<GraduateRequireEntity> getWrapper(GraduateRequireQuery query){
        LambdaQueryWrapper<GraduateRequireEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(GraduateRequireVO vo) {
        GraduateRequireEntity entity = GraduateRequireConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(GraduateRequireVO vo) {
        GraduateRequireEntity entity = GraduateRequireConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

}