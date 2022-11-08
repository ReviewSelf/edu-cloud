package net.edu.module.dao;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.GraduateRequireEntity;
import net.edu.module.query.GraduateRequireQuery;
import net.edu.module.vo.GraduateRequireVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* 毕业要求
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-20
*/
@Mapper
public interface GraduateRequireDao extends BaseDao<GraduateRequireEntity> {

    IPage<GraduateRequireVO> selectGraduateRequireByPage(Page<GraduateRequireVO> page, GraduateRequireQuery query);

    Integer insertGraduateRequire(GraduateRequireVO vo);

    List<GraduateRequireEntity> selectGraduateByGrade(String grade);

    List<GraduateRequireVO> selectWeight(Long id);

    Integer deleteGraduate(Long id);
}
