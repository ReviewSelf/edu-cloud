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
        List<ArchiveTestScoreVO> vos = new ArrayList<>();

        //查出这堂课所有的考试记录
        List<ArchiveTestScoreEntity> list = archiveTestScoreDao.selectTestScoreByCourseId(courseId);

        //查出课程下的所有考核点
        List<ArchiveWeightTargetAssessVO> archiveWeightTargetAssessVOS = archiveWeightTargetAssessDao.selectAssessByCourseId(courseId);
        //统计共有多少个测试点
        int size = 0;
        for (int i = 0; i < archiveWeightTargetAssessVOS.size(); i++) {
            Long assessId = archiveWeightTargetAssessVOS.get(i).getAssessId();
            //查出考核点下的所有测试点
            List<ArchiveWeightAssessTestVO> archiveWeightAssessTestVOS = archiveWeightAssessTestDao.selectTestName(assessId);
            size += archiveWeightAssessTestVOS.size();
        }
        //遍历所有考试记录
        for (int i = 0; i < list.size(); i+=size) {
            List<String> score = new ArrayList<>();
            ArchiveTestScoreVO vo = new ArchiveTestScoreVO();
            //遍历所有测试点
            for (int j = 0; j < size; j++) {
                vo.setStuId(list.get(i).getStuId());
                vo.setStuName(list.get(i).getStuName());
                score.set(j,list.get(i+j).getScore());
            }
            vo.setScore(score);
            vos.add(vo);
        }
        return vos;
    }



}