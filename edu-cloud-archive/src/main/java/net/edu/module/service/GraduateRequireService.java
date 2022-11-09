package net.edu.module.service;



import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.GraduateRequireEntity;
import net.edu.module.query.GraduateRequireQuery;
import net.edu.module.vo.GraduateRequireVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 毕业要求
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-20
 */
public interface GraduateRequireService extends BaseService<GraduateRequireEntity> {

    PageResult<GraduateRequireVO> page(GraduateRequireQuery query);

    void save(GraduateRequireVO vo);

    void update(GraduateRequireVO vo);

    void delete(List<Long> idList);

    List<GraduateRequireEntity> selectGraduateByGrade(String grade);

    void importArchive(MultipartFile file);

    void saveBatchRequire(GraduateRequireVO vo);

    List<GraduateRequireVO> selectWeight(Long id);
}
