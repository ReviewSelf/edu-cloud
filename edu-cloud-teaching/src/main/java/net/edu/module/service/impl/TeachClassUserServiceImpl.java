package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.TeachClassUserConvert;
import net.edu.module.dao.TeachClassUserDao;
import net.edu.module.entity.TeachClassUserEntity;
import net.edu.module.query.TeachClassUserQuery;
import net.edu.module.service.TeachClassUserService;
import net.edu.module.vo.TeachClassUserVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.edu.framework.common.page.PageResult;

import java.util.Date;
import java.util.List;

/**
 * 班级用户表
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-09-16
 */
@Service
@AllArgsConstructor

public class TeachClassUserServiceImpl extends BaseServiceImpl<TeachClassUserDao, TeachClassUserEntity> implements TeachClassUserService {

    private final TeachClassUserDao teachClassUserDao;

    @Override
    public PageResult<TeachClassUserVO> page(TeachClassUserQuery query) {
        IPage<TeachClassUserEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(TeachClassUserConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    @Override
    public List<Long> getUserIdList(Long classId) {
        return teachClassUserDao.selectUserIdList(classId);
    }

    private LambdaQueryWrapper<TeachClassUserEntity> getWrapper(TeachClassUserQuery query){
        LambdaQueryWrapper<TeachClassUserEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(TeachClassUserVO vo) {
        TeachClassUserEntity entity = TeachClassUserConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(TeachClassUserVO vo) {
        TeachClassUserEntity entity = TeachClassUserConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    public void quitClass(Long classId,Long userId, Date quitTime) {
        teachClassUserDao.updateQuitClass(classId,userId,quitTime);
    }

}
