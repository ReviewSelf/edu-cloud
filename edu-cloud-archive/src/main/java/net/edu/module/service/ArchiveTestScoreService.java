package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.vo.ArchiveTestScoreVO;
import net.edu.module.query.ArchiveTestScoreQuery;
import net.edu.module.entity.ArchiveTestScoreEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 考试成绩表
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-11-16
 */
public interface ArchiveTestScoreService extends BaseService<ArchiveTestScoreEntity> {



    List<ArchiveTestScoreVO> selectTestScoreByCourseId(Long courseId);

    void scoreImportExcel(MultipartFile file,Long courseId);

    int insertArchiveTestScore(String stuId, String stuName, List list);

}
