package net.edu.module.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.dao.EnrollLessonDao;
import net.edu.module.dao.EnrollUserDao;
import net.edu.module.entity.*;
import net.edu.module.query.EnrollLessonQuery;
import net.edu.module.service.EnrollLessonService;
import net.edu.module.service.StudentService;
import net.edu.module.vo.EnrollLessonVO;
import net.edu.module.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EnrollLessonServiceImpl extends BaseServiceImpl<EnrollLessonDao, EnrollLessonEntity> implements EnrollLessonService {

    private final PasswordEncoder passwordEncoder;
    private final StudentService userService;
    @Autowired
    private EnrollLessonDao enrollLessonDao;
    private EnrollUserDao enrollUserDao;
    @Override
    public PageResult<EnrollLessonVO> page(EnrollLessonQuery query) {
        Page<EnrollLessonVO> page = new Page<>(query.getPage(), query.getLimit());
        IPage<EnrollLessonVO> list =enrollLessonDao.selectEnrollLessonByPage(page,query);
        return new PageResult<>(list.getRecords(), page.getTotal());
    }
    @Override
    public EnrollLessonEntity getLessonById(Long id) {
        return enrollLessonDao.selectLessonById(id);
    }

    @Override
    public void updateLesson(EnrollLessonVO vo) {
        enrollLessonDao.updateLesson(vo);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    public List<EnrollSelectOne> getSelectOne() {
        return enrollLessonDao.SelectOne();
    }

    @Override
    public void saveLesson(EnrollLessonVO vo) {
        enrollLessonDao.saveLesson(vo);
    }

    @Override
    public void joinLesson(EnrollJoinLessonEntity entity) {
        enrollLessonDao.joinLesson(entity);
        EnrollUserEntity user = enrollLessonDao.selectUserById(entity.getId());
        Integer studentId = entity.getId();
        Integer tryLesson = entity.getTryLesson();
        System.out.println(user);

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
//        enrollLessonDao.joinLessonSys(user);
        enrollLessonDao.joinLessonEvalute(user,studentId , tryLesson);
        enrollLessonDao.joinLessonLog(userId , tryLesson);
    }

    @Override
    public void updateOpinion(EnrollOpinionEntity entity) {
        enrollLessonDao.updateOpinion(entity);
    }


}
