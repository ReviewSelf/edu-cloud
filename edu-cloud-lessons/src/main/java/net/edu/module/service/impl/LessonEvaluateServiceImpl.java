package net.edu.module.service.impl;


import lombok.AllArgsConstructor;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.api.EduWxApi;
import net.edu.module.dao.LessonEvaluateDao;
import net.edu.module.entity.LessonEvaluateEntity;
import net.edu.module.service.LessonEvaluateService;
import net.edu.module.vo.LessonEvaluateVO;
import net.edu.module.vo.WxLessonEvaluationVO;
import org.springframework.stereotype.Service;

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

    EduWxApi eduWxApi;

    @Override
    public List<LessonEvaluateVO> list(Long lessonId) {
        return baseMapper.list(lessonId);
    }

    @Override
    public void save(List<LessonEvaluateVO> list) {

        baseMapper.save(list);
    }

    @Override
    public void update(LessonEvaluateVO vo) {
        baseMapper.updateByUserId(vo);
    }


    @Override
    public void sendEvaluate(Long lessonId){
        List<WxLessonEvaluationVO> list =baseMapper.selectEvaluate(lessonId);
        eduWxApi.insertLessonEvaluationTemplate(list);

    }
}