package net.edu.module.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import lombok.AllArgsConstructor;
import net.edu.framework.common.cache.RedisKeys;
import net.edu.framework.common.utils.RedisUtils;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.api.EduTeachApi;
import net.edu.module.convert.LessonResourceConvert;
import net.edu.module.entity.LessonResourceEntity;
import net.edu.module.vo.LessonResourceVO;
import net.edu.module.dao.LessonResourceDao;
import net.edu.module.service.LessonResourceService;
import net.edu.module.vo.TeachPlanItemResourceVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 教学日历资源表
 *
 * @author 马佳浩 babamu@126.com
 * @since 1.0.0 2022-09-15
 */
@Service
@AllArgsConstructor
public class LessonResourceServiceImpl extends BaseServiceImpl<LessonResourceDao, LessonResourceEntity> implements LessonResourceService {

    private final EduTeachApi eduTeachApi;

    private final RedisUtils redisUtils;



    @Override
    public void save(LessonResourceVO vo) {
        LessonResourceEntity entity = LessonResourceConvert.INSTANCE.convert(vo);
        baseMapper.insert(entity);
        redisUtils.del(RedisKeys.getLessonResources(vo.getLessonId()));
    }


    /**
     * 开班后自动根据教学日历资源生成课堂资源
     * @param planItemId
     * @param lessonId
     */
    @Override
    public void copyFromPlanItem(Long planItemId,Long lessonId) {
        //根据id获取资源列表
        List<TeachPlanItemResourceVO> list =eduTeachApi.getItemResource(planItemId).getData();
        if(!CollectionUtil.isEmpty(list)){
            // 插入至数据库
            baseMapper.insertResourceList(list,lessonId);
        }

    }

    @Override
    public List<LessonResourceVO> getLessonResource(Long lessonId) {
        List<LessonResourceVO> lessonResourceVOS=null;
        lessonResourceVOS= (List<LessonResourceVO>) redisUtils.get(RedisKeys.getLessonResources(lessonId),RedisUtils.HOUR_ONE_EXPIRE);
        if(lessonResourceVOS==null){
            lessonResourceVOS=baseMapper.selectLessonResource(lessonId);
            redisUtils.set(RedisKeys.getLessonResources(lessonId),lessonResourceVOS,RedisUtils.HOUR_ONE_EXPIRE);
        }
        return lessonResourceVOS;
    }

    @Override
    public void deleteResource(Long id) {
        LessonResourceEntity entity=baseMapper.selectById(id);
        redisUtils.del(RedisKeys.getLessonResources(entity.getLessonId()));
        baseMapper.deleteResource(id);
    }
}