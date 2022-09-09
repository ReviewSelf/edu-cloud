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
import net.edu.module.entity.EnrollLessonEntity;
import net.edu.module.entity.EnrollUserEntity;
import net.edu.module.query.EnrollLessonQuery;
import net.edu.module.service.EnrollLessonService;
import net.edu.module.service.EnrollUserService;
import net.edu.module.vo.EnrollLessonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EnrollLessonServiceImpl extends BaseServiceImpl<EnrollLessonDao, EnrollLessonEntity> implements EnrollLessonService {

    @Autowired
    private EnrollLessonDao enrollLessonDao;
    @Override
    public PageResult<EnrollLessonVO> page(EnrollLessonQuery query) {
        Page<EnrollLessonVO> page = new Page<>(query.getPage(), query.getLimit());
        IPage<EnrollLessonVO> list =enrollLessonDao.getEnrollLessonByPage(page,query);
        return new PageResult<>(list.getRecords(), page.getTotal());
    }

    @Override
    public void save(EnrollLessonVO vo) {

        EnrollLessonEntity entity = EnrollLessonConvert.INSTANCE.convert(vo);

        System.out.println("xxxxxx:" + entity);
        baseMapper.insert(entity);
    }

}