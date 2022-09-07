package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.EnrollUserConvert;
import net.edu.module.dao.EnrollUserDao;
import net.edu.module.entity.EnrollUserEntity;
import net.edu.module.query.EnrollUserQuery;
import net.edu.module.vo.EnrollUserVO;
import net.edu.module.service.EnrollUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * XinXiHeShi
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-09-05
 */
@Service
@AllArgsConstructor
public class EnrollUserServiceImpl extends BaseServiceImpl<EnrollUserDao, EnrollUserEntity> implements EnrollUserService {

    @Override
    public PageResult<EnrollUserVO> page(EnrollUserQuery query) {
        IPage<EnrollUserEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(EnrollUserConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private LambdaQueryWrapper<EnrollUserEntity> getWrapper(EnrollUserQuery query){
        LambdaQueryWrapper<EnrollUserEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(EnrollUserVO vo) {
        EnrollUserEntity entity = EnrollUserConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(EnrollUserVO vo) {
        EnrollUserEntity entity = EnrollUserConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

}
