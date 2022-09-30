package net.edu.module.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.RedisUtils;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.api.EduProblemApi;
import net.edu.module.api.EduTeachApi;
import net.edu.module.convert.LessonProblemConvert;
import net.edu.module.entity.LessonProblemEntity;
import net.edu.module.query.LessonProblemQuery;
import net.edu.module.vo.LessonProblemVO;
import net.edu.module.dao.LessonProblemDao;
import net.edu.module.service.LessonProblemService;
import net.edu.module.vo.ProblemPaperItemEntity;
import net.edu.module.vo.TeachPlanItemPaperVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 课堂练习表
 *
 * @author 马佳浩 
 * @since 1.0.0 2022-09-15
 */
@Service
@AllArgsConstructor
public class LessonProblemServiceImpl extends BaseServiceImpl<LessonProblemDao, LessonProblemEntity> implements LessonProblemService {

    private final EduTeachApi eduTeachApi;
    private final EduProblemApi eduProblemApi;


    private final RedisUtils redisUtils;

    @Override
    public List<LessonProblemVO> list(LessonProblemQuery query) {
        return baseMapper.selectLessonProblem(query);
    }


    @Override
    public void save(LessonProblemVO vo) {
        LessonProblemEntity entity = LessonProblemConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(LessonProblemVO vo) {
        LessonProblemEntity entity = LessonProblemConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }


    //开班时启用
    @Override
    public void copyFromPlanItem(Long planItemId, Long lessonId) {
        //先获取试卷及其类型
        List<TeachPlanItemPaperVO> paperList=eduTeachApi.getItemPaper(planItemId).getData();
        if(!CollectionUtil.isEmpty(paperList)){
            for (TeachPlanItemPaperVO paper : paperList){
                //把试卷拆解成一个个的题目及类型
                List<ProblemPaperItemEntity> problemList=eduProblemApi.getPaperProblem(paper.getPaperId()).getData();
                if(!CollectionUtil.isEmpty(problemList)){
                    // 插入至数据库
                    baseMapper.insertProblemList(problemList,paper.getPaperType(),lessonId);
                }

            }
        }
    }

    @Override
    @Transactional
    public void updateProblemTime(List<LessonProblemVO> lessonProblemList) {
        if (!CollectionUtil.isEmpty(lessonProblemList)){
            for (LessonProblemVO vo:lessonProblemList){
                update(vo);
            }
        }
    }

    @Override
    public void insertProblemListByTeacher(List<ProblemPaperItemEntity> list,Long lessonId){
        baseMapper.insertProblemListByTeacher(list,lessonId);
    }
}