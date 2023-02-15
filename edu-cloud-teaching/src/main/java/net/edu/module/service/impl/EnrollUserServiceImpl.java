package net.edu.module.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.EnrollUserConvert;
import net.edu.module.dao.EnrollLessonDao;
import net.edu.module.dao.EnrollUserDao;
import net.edu.module.entity.EnrollUserEntity;
import net.edu.module.query.EnrollUserQuery;
import net.edu.module.service.EnrollLessonService;
import net.edu.module.service.EnrollUserService;
import net.edu.module.service.StudentService;
import net.edu.module.vo.EnrollUserVO;
import net.edu.module.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    private final PasswordEncoder passwordEncoder;
    private final StudentService userService;
    private final EnrollLessonDao enrollLessonDao;

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

    @Override
    public void insertSysUser(Integer id) {
        EnrollUserEntity user = enrollLessonDao.selectUserById(id);
        UserVO vo=new UserVO();
        vo.setPassword("123456");
        vo.setStatus(1);
        vo.setGender(0);
        vo.setRealName(user.getRealName());
        vo.setUsername(user.getPhone());
        vo.setMobile(user.getPhone());
        vo.setGrade(user.getGrade());
        vo.setOpenId(user.getOpenId());
        vo.setUnionId(user.getUnionId());
        vo.setArea(user.getArea());
        vo.setOrgId(3L);
        List<Long> RoleIdList=new ArrayList<>();
        RoleIdList.add(2L);
        vo.setRoleIdList(RoleIdList);
        if (StrUtil.isBlank(vo.getPassword())){
            Result.error("密码不能为空");
        }

        // 密码加密
        vo.setPassword(passwordEncoder.encode(vo.getPassword()));

        // 保存
        Long userId=userService.save(vo);
        System.out.println("新增用户的id"+vo.getId());
        if(userId!=null){
            System.out.println("改变状态");
            enrollUserDao.updateStatus3(id);
        }
        System.out.println(vo.getId());
    }
}
