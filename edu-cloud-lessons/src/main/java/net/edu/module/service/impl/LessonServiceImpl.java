package net.edu.module.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import net.edu.framework.common.cache.RedisKeys;
import net.edu.framework.common.utils.RedisUtils;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.api.EduTeachApi;
import net.edu.module.convert.LessonConvert;
import net.edu.module.entity.LessonEntity;
import net.edu.module.query.LessonQuery;
import net.edu.module.service.LessonAttendLogService;
import net.edu.module.service.LessonProblemService;
import net.edu.module.service.LessonResourceService;
import net.edu.module.vo.LessonAttendLogVO;
import net.edu.module.vo.LessonVO;
import net.edu.module.dao.LessonDao;
import net.edu.module.service.LessonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程表
 *
 * @author 马佳浩
 * @since 1.0.0 2022-09-15
 */
@Service
@AllArgsConstructor
public class LessonServiceImpl extends BaseServiceImpl<LessonDao, LessonEntity> implements LessonService {


   private final LessonProblemService lessonProblemService;
    private final LessonResourceService lessonResourceService;

    private final LessonAttendLogService lessonAttendLogService;

    private final LessonDao lessonDao;

    private final RedisUtils redisUtils;

    @Override
    public List<LessonVO> list(LessonQuery query) {
        List<LessonEntity> list =null;
        list= (List<LessonEntity>) redisUtils.get(RedisKeys.getClassLesson(query.getClassId()),RedisUtils.MIN_TEN_EXPIRE);
        if(list==null){
            LambdaQueryWrapper<LessonEntity> wrapper = Wrappers.lambdaQuery();
            wrapper.eq(true, LessonEntity::getClassId, query.getClassId());
            list=baseMapper.selectList(wrapper);
            redisUtils.set(RedisKeys.getClassLesson(query.getClassId()),list,RedisUtils.MIN_TEN_EXPIRE);
        }
        return LessonConvert.INSTANCE.convertList(list);
    }


    @Override
    @Transactional
    public void createLessons(List<LessonVO> voList) {

        if(!CollectionUtil.isEmpty(voList)){
            //第一堂课状态设置进行中
            voList.get(0).setStatus(0);

            for (int i=0;i<voList.size();i++){
                LessonVO item=voList.get(i);
                //插入课程
                LessonEntity entity = LessonConvert.INSTANCE.convert(item);
                baseMapper.insert(entity);

                if(i==0){
                    //生成第一堂课的学生签到表//插入签到表
                    lessonAttendLogService.copyUserFromClassUser(voList.get(0).getClassId(),entity.getId());
                }
                //拷贝教学题目，生成课堂题目
                lessonProblemService.copyFromPlanItem(item.getPlanItemId(),entity.getId());
                //拷贝教学资源，生成课堂资源
                lessonResourceService.copyFromPlanItem(item.getPlanItemId(),entity.getId());
            }



        }



    }

    @Override
    public List<LessonVO> getListById(Long classId) {
        return lessonDao.getListById(classId);
    }

//    @Override
//    public void save(LessonVO vo) {
//        LessonEntity entity = LessonConvert.INSTANCE.convert(vo);
//
//        baseMapper.insert(entity);
//    }

    @Override
    public void update(LessonVO vo) {
        LessonEntity entity = LessonConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }



}
