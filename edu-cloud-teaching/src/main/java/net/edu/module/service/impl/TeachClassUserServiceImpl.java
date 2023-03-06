package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.framework.security.user.SecurityUser;
import net.edu.module.api.EduLessonApi;
import net.edu.module.convert.TeachClassUserConvert;
import net.edu.module.dao.TeachClassUserDao;
import net.edu.module.dao.TeachClassRecordDao;
import net.edu.module.entity.TeachClassUserEntity;
import net.edu.module.query.TeachClassUserQuery;
import net.edu.module.service.StudentService;
import net.edu.module.service.TeachClassUserService;
import net.edu.module.vo.ClassUserRecordVO;
import net.edu.module.vo.LessonStudentVO;
import net.edu.module.vo.TeachClassUserDataVO;
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

    private final EduLessonApi eduLessonApi;

    private final TeachClassRecordDao teachClassRecordDao;


    @Override
    public PageResult<TeachClassUserVO> page(TeachClassUserQuery query) {
        Page<TeachClassUserVO> page = new Page<>(query.getPage() , query.getLimit());
        IPage<TeachClassUserVO> list = teachClassUserDao.page(page, query);
        return new PageResult<>(list.getRecords() , list.getTotal());
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
    @Transactional(rollbackFor = Exception.class)
    public void insertClassUserOne(TeachClassUserDataVO vo) {
        //插入班级
        teachClassUserDao.insertClassUserOne(vo.getJoinClassId(),vo.getUserId(),vo.getJoinTime());
        //插入课程
        if(vo.getJoinLessonList().size() != 0) {
            LessonStudentVO vo1 = new LessonStudentVO();
            vo1.setStuId(vo.getUserId());
            vo1.setClassId(vo.getJoinLessonList());
            eduLessonApi.insertLessonList(vo1);
        }

        //保存插班记录
        ClassUserRecordVO vo2 = new ClassUserRecordVO();
        vo2.setType(vo.getType());
        vo2.setTeacherId(SecurityUser.getUserId());
        vo2.setTime(vo.getJoinTime());
        vo2.setStudentId(vo.getUserId());
        vo2.setNewClassId(vo.getJoinClassId());
        insertClassUserRecord(vo2);
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
    @Transactional(rollbackFor = Exception.class)
    public void quitClass(TeachClassUserDataVO vo) {
        //退出课堂
        if(vo.getQuiteLessonList().size() != 0){
            LessonStudentVO vo1 = new LessonStudentVO();
            vo1.setStuId(vo.getUserId());
            vo1.setClassId(vo.getQuiteLessonList());
            System.out.println(vo1);
            eduLessonApi.deleteLessonList(vo1);
        }
        //退出班级表
        teachClassUserDao.updateQuitClass(vo.getQuiteClassId(),vo.getUserId(),vo.getQuitTime());

        //保存退班记录
        ClassUserRecordVO vo2 = new ClassUserRecordVO();
        vo2.setTeacherId(SecurityUser.getUserId());
        vo2.setType(vo.getType());
        vo2.setTime(vo.getQuitTime());
        vo2.setStudentId(vo.getUserId());
        vo2.setOldClassId(vo.getQuiteClassId());
        insertClassUserRecord(vo2);
    }

    @Override
    public void updateHomeworkTimes(Long userId, Long classId, Integer num) {
        teachClassUserDao.updateHomeworkTimes(userId,classId,num);
    }

    @Override
    public void insertClassUserRecord(ClassUserRecordVO vo) {
        teachClassRecordDao.insertClassUserRecord(vo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changeClassUser(TeachClassUserDataVO vo) {
        //退出课堂
        if(vo.getQuiteLessonList().size() != 0){
            LessonStudentVO vo1 = new LessonStudentVO();
            vo1.setStuId(vo.getUserId());
            vo1.setClassId(vo.getQuiteLessonList());
            System.out.println(vo1);
            eduLessonApi.deleteLessonList(vo1);
        }
        //退出班级表
        teachClassUserDao.updateQuitClass(vo.getQuiteClassId(),vo.getUserId(),vo.getQuitTime());

        //插入班级
        teachClassUserDao.insertClassUserOne(vo.getJoinClassId(),vo.getUserId(),vo.getJoinTime());
        //插入课程
        if(vo.getJoinLessonList().size() != 0) {
            LessonStudentVO vo2 = new LessonStudentVO();
            vo2.setStuId(vo.getUserId());
            vo2.setClassId(vo.getJoinLessonList());
            eduLessonApi.insertLessonList(vo2);
        }
        //保存记录
        ClassUserRecordVO vo3 = new ClassUserRecordVO();
        vo3.setTeacherId(SecurityUser.getUserId());
        vo3.setType(vo.getType());
        vo3.setTime(vo.getQuitTime());
        vo3.setStudentId(vo.getUserId());
        vo3.setOldClassId(vo.getQuiteClassId());
        vo3.setNewClassId(vo.getJoinClassId());
        insertClassUserRecord(vo3);
    }

}
