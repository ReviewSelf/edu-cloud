package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.EnrollUserConvert;
import net.edu.module.dao.EnrollClassDao;
import net.edu.module.dao.EnrollUserDao;
import net.edu.module.entity.EnrollClassEntity;
import net.edu.module.entity.EnrollUserEntity;
import net.edu.module.query.EnrollUserQuery;
import net.edu.module.vo.EnrollUserVO;
import net.edu.module.service.EnrollUserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private EnrollUserDao enrollUserDao;
    @Override
    public PageResult<EnrollUserVO> page(EnrollUserQuery query) {
        Page<EnrollUserVO> page = new Page<>(query.getPage(), query.getLimit());
        IPage<EnrollUserVO> list =enrollUserDao.getEnrollUserByPage(page,query);
        return new PageResult<>(list.getRecords(), page.getTotal());
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



    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteEnrollUser(long id) {
        enrollUserDao.deleteEnrollUser(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public  void confirm(Integer id){
        enrollUserDao.confirmEnrollUser(id);
    }

}
