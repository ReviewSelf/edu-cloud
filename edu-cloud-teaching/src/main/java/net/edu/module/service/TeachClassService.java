package net.edu.module.service;

import io.swagger.v3.oas.models.security.SecurityScheme;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.vo.TeachClassVO;
import net.edu.module.query.TeachClassQuery;
import net.edu.module.entity.TeachClassEntity;
import net.edu.module.vo.TeachPlanItemVO;
import net.edu.module.vo.TeachPlanPaperVo;
import net.edu.module.vo.TeachPriceVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 班级表
 *
 * @author wengruichen babamu@126.com
 * @since 1.0.0 2022-09-09
 */
public interface TeachClassService extends BaseService<TeachClassEntity> {

    PageResult<TeachClassVO> page(TeachClassQuery query);

    List<TeachPlanItemVO> selectLesson(@Param("id") Long id);

    void save(TeachClassVO vo);

    void update(TeachClassVO vo);

    void delete(List<Long> idList);

    List<TeachClassEntity> getOldClassUser(Long userId);

    List<TeachClassVO> getClassForStudent(Integer status);

    List<TeachClassVO> getClassForTeacher(Integer status);

    void updateNextLesson(Integer nextLesson,Long classId);

    void endingCalss(Integer id);

    List<TeachClassVO> getOpenClassesList();


    TeachClassVO getClassById(Long id);
}
