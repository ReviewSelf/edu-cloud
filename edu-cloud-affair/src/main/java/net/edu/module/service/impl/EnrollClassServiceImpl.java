package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.EnrollClassConvert;
import net.edu.module.dao.EnrollClassDao;
import net.edu.module.entity.EnrollClassEntity;
import net.edu.module.query.EnrollClassQuery;
import net.edu.module.service.EnrollClassService;
import net.edu.module.vo.EnrollClassVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 班级发布
 *
 * @author 翁瑞辰 
 * @since  2022-09-06
 */
@Service
@AllArgsConstructor
public class EnrollClassServiceImpl extends BaseServiceImpl<EnrollClassDao, EnrollClassEntity> implements EnrollClassService {

    @Autowired
    private EnrollClassDao enrollClassDao;
    @Override
    public IPage<EnrollClassEntity> getEnrollClass(Integer pageIndex, Integer pageSize) {
        Page<EnrollClassEntity> page = new Page<>(pageIndex, pageSize);
        return enrollClassDao.getEnrollClassByPage(page);
    }

    private LambdaQueryWrapper<EnrollClassEntity> getWrapper(EnrollClassQuery query){
        LambdaQueryWrapper<EnrollClassEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(EnrollClassVO vo) {
        EnrollClassEntity entity = EnrollClassConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(EnrollClassVO vo) {
        EnrollClassEntity entity = EnrollClassConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteEnrollClass(long id) {
        enrollClassDao.deleteEnrollClass(id);
    }

    @Override
    public IPage<EnrollClassEntity> select(Integer pageIndex, Integer pageSize,  String className) {
        Page<EnrollClassEntity> page = new Page<>(pageIndex, pageSize);
        if(className == null ){
            return enrollClassDao.getEnrollClassByPage(page);
        }
        return enrollClassDao.select(page,className);
    }

    @Override
    public void updateStatus(Long id) {
        enrollClassDao.updateStatus(id);
    }

}