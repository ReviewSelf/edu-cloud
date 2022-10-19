package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.LessonEvaluateConvert;
import net.edu.module.dao.LessonEvaluateDao;
import net.edu.module.entity.ExamProblemEntity;
import net.edu.module.entity.LessonEvaluateEntity;
import net.edu.module.query.LessonEvaluateQuery;
import net.edu.module.service.LessonEvaluateService;
import net.edu.module.vo.LessonEvaluateVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 课堂评价
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-18
 */
@Service
@AllArgsConstructor
public class LessonEvaluateServiceImpl extends BaseServiceImpl<LessonEvaluateDao, LessonEvaluateEntity> implements LessonEvaluateService {

    private LessonEvaluateDao lessonEvaluateDao;

    @Override
    public List<LessonEvaluateVO> list(Long lessonId) {
        return lessonEvaluateDao.list(lessonId);
    }

    @Override
    public void save(List<LessonEvaluateVO> list) {

        lessonEvaluateDao.save(list);
    }

    @Override
    public void update(LessonEvaluateVO vo) {
        lessonEvaluateDao.updateByUserId(vo);
    }

}