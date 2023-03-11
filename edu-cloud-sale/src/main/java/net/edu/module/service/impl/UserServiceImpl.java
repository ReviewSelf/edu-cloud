package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import net.edu.framework.common.exception.ServerException;
import net.edu.framework.common.page.PageResult;
import net.edu.module.convert.UserConvert;
import net.edu.module.dao.EnrollDao;
import net.edu.module.dao.TeachClassHoursDao;
import net.edu.module.dao.UserDao;
import net.edu.module.dao.UserRoleDao;
import net.edu.module.entity.TeachClassHoursEntity;
import net.edu.module.entity.UserEntity;
import net.edu.module.query.UserQuery;
import net.edu.module.service.UserService;
import net.edu.module.vo.TeachClassHoursVO;
import net.edu.module.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * @author weng
 * @date 2023/1/13 - 13:21
 **/
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private EnrollDao enrollDao;
    @Autowired
    private TeachClassHoursDao teachClassHoursDao;

    @Autowired
    private UserRoleDao userRoleDao;

    private final PasswordEncoder passwordEncoder;

    @Override
    public PageResult<UserVO> page(UserQuery query) {
        Page<UserVO> page = new Page<>(query.getPage(), query.getLimit());
        IPage<UserVO> list = userDao.selectStudentByPage(page,query);
        return new PageResult<>(list.getRecords(), page.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized void save(UserVO vo) {
        check(vo);
        // 保存用户
        vo.setId(null);
        vo.setPassword(passwordEncoder.encode("123456"));
        setStuNumber(vo);
        vo.setUsername(vo.getStuNumber());
        userDao.insertCadet(vo);
        TeachClassHoursEntity teachClassHoursEntity = new TeachClassHoursEntity();
        teachClassHoursEntity.setUserId(vo.getId());
        teachClassHoursDao.insert(teachClassHoursEntity);
        userRoleDao.insertStudentRole(vo.getId());
    }

    @Override
    public void update(UserVO vo) {
        UserEntity entity = UserConvert.INSTANCE.convert(vo);

        // 更新用户
        updateById(entity);
    }

    @Override
    public void delete(List<Long> idList) {
        // 删除用户
        removeByIds(idList);

        // 删除用户角色关系
//        userRoleService.deleteByUserIdList(idList);

    }

    @Override
    public UserVO getByMobile(String mobile) {
        UserEntity user = baseMapper.getByMobile(mobile);

        return UserConvert.INSTANCE.convert(user);
    }

    private void check(UserVO vo) {
        UserEntity entity = UserConvert.INSTANCE.convert(vo);

        // 判断用户名是否存在
        UserEntity user = baseMapper.getByUsername(entity.getUsername());
        if (user != null) {
            throw new ServerException("用户名已经存在");
        }

        // 判断手机号是否存在
        user = baseMapper.getByMobile(entity.getMobile());
        if (user != null) {
            throw new ServerException("手机号已经存在");
        }
    }

    @Override
    @Transactional
    public void insertCadet(UserVO vo) {
        check(vo);
        Long oldId = vo.getId();

        setStuNumber(vo);
        vo.setUsername(vo.getStuNumber());
        vo.setId(null);
        vo.setPassword(passwordEncoder.encode("123456"));
        userDao.insertCadet(vo);
        TeachClassHoursEntity teachClassHoursEntity = new TeachClassHoursEntity();
        teachClassHoursEntity.setUserId(vo.getId());
        teachClassHoursDao.insert(teachClassHoursEntity);
        userRoleDao.insertStudentRole(vo.getId());
        enrollDao.updateStatus(oldId,vo.getId());
    }

    private void setStuNumber(UserVO vo) {
        String stuNumber = selectStuNumber();
        if(stuNumber!=null){
            vo.setStuNumber(String.valueOf((Long.parseLong(stuNumber)+1)));
        }
        else {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            System.out.println(year);
            vo.setStuNumber(year+"001");
        }
    }

    @Override
    public List<Integer> selectUserStatus(Long id) {
        return userDao.selectUserStatus(id);
    }

    @Override
    public String selectStuNumber() {
        return userDao.selectStuNumber();
    }

    @Override
    public TeachClassHoursVO getStudentPay(Long id) {
        return teachClassHoursDao.getStudentPay(id);
    }

    @Override
    public List<UserVO> selectSaleName() {
        return userDao.selectSaleName();
    }


    @Override
    public void insertTeachClassHours(Long userId){
        TeachClassHoursEntity entity = new TeachClassHoursEntity();
        entity.setUserId(userId);
        System.out.println(entity);
        teachClassHoursDao.insert(entity);
    }




}