package net.edu.module.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.TeachClassEntity;
import net.edu.module.query.TeachClassQuery;
import net.edu.module.vo.TeachClassVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教学计划表
 *
 * @author 阿沐
 * @since 1.0.0 2022-09-08
 */
@Mapper
public interface TeachClassDao extends BaseDao<TeachClassEntity> {

    IPage<TeachClassVO> page(Page<TeachClassVO> page, @Param("query") TeachClassQuery query);

    List<TeachClassVO> selectClassForStudent(Long userId, Integer status);

    List<TeachClassVO> selectClassForTeacher(Long userId,Integer status);

    List<TeachClassEntity> selectOldClassUser(Long userId);

    void updateNextLesson(Integer nextLesson,Long classId);

    void endingClass(Integer id);

    List<TeachClassVO> selectOpenClasses();


}
