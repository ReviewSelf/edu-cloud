package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.EnrollLessonConvert;
import net.edu.module.convert.EnrollUserConvert;
import net.edu.module.dao.EnrollLessonDao;
import net.edu.module.dao.EnrollUserDao;
import net.edu.module.entity.*;
import net.edu.module.query.EnrollLessonQuery;
import net.edu.module.service.EnrollLessonService;
import net.edu.module.service.EnrollUserService;
import net.edu.module.vo.EnrollLessonVO;
import net.edu.module.vo.EnrollUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class EnrollLessonServiceImpl extends BaseServiceImpl<EnrollLessonDao, EnrollLessonEntity> implements EnrollLessonService {

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
    }

    @Override
    public void joinLessonSys(EnrollUserEntity user) {
        enrollLessonDao.joinLessonSys(user);
    }


}
