package net.edu.module.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import net.edu.framework.common.cache.RedisKeys;
import net.edu.framework.common.exception.ServerException;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.RedisUtils;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.framework.security.user.SecurityUser;
import net.edu.module.api.EduProblemApi;
import net.edu.module.api.EduTeachApi;
import net.edu.module.convert.LessonProblemConvert;
import net.edu.module.entity.LessonProblemEntity;
import net.edu.module.entity.LessonResourceEntity;
import net.edu.module.query.LessonProblemQuery;
import net.edu.module.vo.ExamAttendLogVO;
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

        List<LessonProblemVO> list= (List<LessonProblemVO>) redisUtils.get(RedisKeys.getLessonProblem(query.getLessonId(),query.getType()));

        if(list==null){
            list= baseMapper.selectLessonProblem(query);
            redisUtils.set(RedisKeys.getLessonProblem(query.getLessonId(),query.getType()),list,RedisUtils.MIN_TEN_EXPIRE);
        }
        return list;
    }


    @Override
    public void save(LessonProblemVO vo) {
        LessonProblemEntity entity = LessonProblemConvert.INSTANCE.convert(vo);
        try{
            baseMapper.insert(entity);
        }catch (Exception e){
            throw new ServerException("已存在");
        }

        redisUtils.delByPre(RedisKeys.getLessonProblem(vo.getLessonId(),null));
    }

    @Override
    public void update(LessonProblemVO vo) {
        LessonProblemEntity entity = LessonProblemConvert.INSTANCE.convert(vo);
        updateById(entity);
        redisUtils.delByPre(RedisKeys.getLessonProblem(vo.getLessonId(),null));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        LessonProblemEntity entity=baseMapper.selectById(idList.get(0));
        removeByIds(idList);
        redisUtils.delByPre(RedisKeys.getLessonProblem(entity.getLessonId(),null));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProblem(List<LessonProblemVO> lessonProblemList) {
        if (!CollectionUtil.isEmpty(lessonProblemList)){
            for (LessonProblemVO vo:lessonProblemList){
                update(vo);
            }
        }
    }

    @Override
    public void insertProblemListByTeacher(List<ProblemPaperItemEntity> list,Long lessonId){
        baseMapper.insertProblemListByTeacher(list,lessonId);
        redisUtils.delByPre(RedisKeys.getLessonProblem(lessonId,null));
    }

    @Override
    public PageResult<LessonProblemVO> unfinishedPage(Integer page, Integer limit) {
        Long userId= SecurityUser.getUserId();
        Page<LessonProblemVO> ipage = new Page<>(page,limit);
        IPage<LessonProblemVO> list = baseMapper.selectUnfinishedPage(ipage,userId);
        return new PageResult<>(list.getRecords(), list.getTotal());
    }


    /**
     * 开班时启用
     * @param planItemId
     * @param lessonId
     */
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
}