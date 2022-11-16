package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.ArchiveTestScoreConvert;
import net.edu.module.dao.ArchiveWeightAssessTestDao;
import net.edu.module.dao.ArchiveWeightTargetAssessDao;
import net.edu.module.entity.ArchiveTestScoreEntity;
import net.edu.module.entity.ArchiveWeightAssessTestEntity;
import net.edu.module.query.ArchiveTestScoreQuery;
import net.edu.module.utils.ExamExcelUtil;
import net.edu.module.vo.ArchiveExamAttendLogVO;
import net.edu.module.vo.ArchiveTestScoreVO;
import net.edu.module.dao.ArchiveTestScoreDao;
import net.edu.module.service.ArchiveTestScoreService;
import net.edu.module.vo.ArchiveWeightAssessTestVO;
import net.edu.module.vo.ArchiveWeightTargetAssessVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 考试成绩表
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-11-16
 */
@Service
@AllArgsConstructor
public class ArchiveTestScoreServiceImpl extends BaseServiceImpl<ArchiveTestScoreDao, ArchiveTestScoreEntity> implements ArchiveTestScoreService {

    @Autowired
    private ArchiveTestScoreDao archiveTestScoreDao;
    @Autowired
    private ArchiveWeightAssessTestDao archiveWeightAssessTestDao;
    @Autowired
    private ArchiveWeightTargetAssessDao archiveWeightTargetAssessDao;



    @Override
    public List<ArchiveTestScoreVO> selectTestScoreByCourseId(Long courseId) {
        List<ArchiveTestScoreVO> vos = null;
        List<ArchiveTestScoreEntity> list = archiveTestScoreDao.selectTestScoreByCourseId(courseId);
        List<ArchiveWeightTargetAssessVO> archiveWeightTargetAssessVOS = archiveWeightTargetAssessDao.selectAssessByCourseId(courseId);
        Long assessId = archiveWeightTargetAssessVOS.get(0).getAssessId();
        List<ArchiveWeightAssessTestVO> archiveWeightAssessTestVOS = archiveWeightAssessTestDao.selectTestName(assessId);
        int size = archiveWeightAssessTestVOS.size();
        for (int i = 0; i < list.size(); i+=size) {
            int k=0;
            List<String> score = null;
            ArchiveTestScoreVO vo = null;
            for (int j = 0; j < size; j++) {
                vo.setStuId(list.get(i).getStuId());
                vo.setStuName(list.get(i).getStuName());
                score.set(j,list.get(i+j).getScore());
            }
            vo.setScore(score);
            vos.set(k,vo);
            k++;
        }
        return vos;
    }



}