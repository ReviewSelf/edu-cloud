package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.EnrollUserConvert;
import net.edu.module.dao.EnrollUserDao;
import net.edu.module.entity.EnrollUserEntity;
import net.edu.module.query.EnrollUserQuery;
import net.edu.module.service.EnrollUserService;
import net.edu.module.vo.EnrollUserVO;
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
        IPage<EnrollUserVO> list =enrollUserDao.selectEnrollUserByPage(page,query);
        return new PageResult<>(list.getRecords(), page.getTotal());
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
    public  void confirm(Integer id){
        enrollUserDao.updateConfirmEnrollUser(id);
    }

    @Override
    public void insertOpenId(String openId, String unionId) {
        enrollUserDao.insertId(openId,unionId);
    }

    @Override
    public void post(EnrollUserVO enrollUserVO){
        System.out.println(enrollUserVO);
        EnrollUserEntity entity = EnrollUserConvert.INSTANCE.convert(enrollUserVO);
        enrollUserDao.save(entity);
    }

    @Override
    public Integer insertEnrollUser(EnrollUserVO enrollUserVO) {
        enrollUserDao.insertEnrollUser(enrollUserVO);
        return enrollUserVO.getId();
    }

    @Override
    public EnrollUserVO selectUserInfoByOpenId(String openId) {
        return enrollUserDao.selectUserInfoByOpenId(openId);
    }

    @Override
    public void insertClassUser(Integer classId, Integer userId) {
        enrollUserDao.insertClassUser(classId,userId);
    }

}
