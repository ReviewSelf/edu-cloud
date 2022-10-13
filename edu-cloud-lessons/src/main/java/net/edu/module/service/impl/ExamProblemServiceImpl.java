package net.edu.module.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import lombok.AllArgsConstructor;
import net.edu.framework.common.cache.RedisKeys;
import net.edu.framework.common.utils.RedisUtils;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.api.EduProblemApi;
import net.edu.module.api.EduTeachApi;
import net.edu.module.convert.ExamProblemConvert;
import net.edu.module.dao.ExamProblemDao;
import net.edu.module.entity.ExamProblemEntity;
import net.edu.module.query.ExamProblemQuery;
import net.edu.module.service.ExamProblemService;
import net.edu.module.vo.ExamProblemVO;
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
public class ExamProblemServiceImpl extends BaseServiceImpl<ExamProblemDao, ExamProblemEntity> implements ExamProblemService {

    private final EduTeachApi eduTeachApi;
    private final EduProblemApi eduProblemApi;


    @Override
    public List<ExamProblemVO> list(ExamProblemQuery query) {

       List<ExamProblemVO>  list= baseMapper.selectExamProblem(query);
        return list;
    }


    @Override
    public void save(ExamProblemVO vo) {
        ExamProblemEntity entity = ExamProblemConvert.INSTANCE.convert(vo);
        baseMapper.insert(entity);
    }

    @Override
    public void update(ExamProblemVO vo) {
        ExamProblemEntity entity = ExamProblemConvert.INSTANCE.convert(vo);
        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        ExamProblemEntity entity=baseMapper.selectById(idList.get(0));
        removeByIds(idList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProblemTime(List<ExamProblemVO> lessonProblemList) {
        if (!CollectionUtil.isEmpty(lessonProblemList)){
            for (ExamProblemVO vo:lessonProblemList){
                update(vo);
            }
        }
    }

    @Override
    public void insertProblemListByTeacher(List<ProblemPaperItemEntity> list,Long lessonId){
        baseMapper.insertProblemListByTeacher(list,lessonId);
    }


}