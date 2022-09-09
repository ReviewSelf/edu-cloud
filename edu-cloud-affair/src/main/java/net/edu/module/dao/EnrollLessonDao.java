package net.edu.module.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.EnrollLessonEntity;
import net.edu.module.query.EnrollLessonQuery;
import net.edu.module.vo.EnrollLessonVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EnrollLessonDao extends BaseDao<EnrollLessonEntity> {

    IPage<EnrollLessonVO> getEnrollLessonByPage(Page<EnrollLessonVO> page, EnrollLessonQuery query);
}
