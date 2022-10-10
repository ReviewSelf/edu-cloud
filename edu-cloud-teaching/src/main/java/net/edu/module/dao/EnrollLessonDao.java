package net.edu.module.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.*;
import net.edu.module.query.EnrollLessonQuery;
import net.edu.module.vo.EnrollLessonVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EnrollLessonDao extends BaseDao<EnrollLessonEntity> {

    IPage<EnrollLessonVO> selectEnrollLessonByPage(Page<EnrollLessonVO> page, EnrollLessonQuery query);

    EnrollLessonEntity selectLessonById(Long id);

    void updateLesson(EnrollLessonVO vo);

    List<EnrollSelectOne> SelectOne();

    void saveLesson(EnrollLessonVO vo);

    void joinLesson(EnrollJoinLessonEntity entity);

    void joinLessonSys(EnrollUserEntity user);

    EnrollUserEntity selectUserById(Integer id);


    void joinLessonEvalute(EnrollUserEntity user, Integer studentId, Integer tryLesson);

    void updateOpinion(EnrollOpinionEntity entity);

    void joinLessonLog(Integer studentId, Integer tryLesson);
}
