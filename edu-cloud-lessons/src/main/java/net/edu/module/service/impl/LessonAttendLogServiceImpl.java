package net.edu.module.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import lombok.AllArgsConstructor;
import net.edu.framework.common.cache.RedisKeys;
import net.edu.framework.common.utils.RedisUtils;
import net.edu.framework.common.utils.Result;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.api.EduTeachApi;
import net.edu.module.convert.LessonAttendLogConvert;
import net.edu.module.dao.LessonAttendLogDao;
import net.edu.module.entity.LessonAttendLogEntity;
import net.edu.module.query.LessonAttendLogQuery;
import net.edu.module.service.LessonAttendLogService;
import net.edu.module.vo.LessonAttendLogVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 课堂签到表
 *
 * @author 马佳浩 
 * @since 1.0.0 2022-09-15
 */
@Service
@AllArgsConstructor
public class LessonAttendLogServiceImpl extends BaseServiceImpl<LessonAttendLogDao, LessonAttendLogEntity> implements LessonAttendLogService {

    private final EduTeachApi eduTeachApi;
    private final LessonAttendLogDao lessonAttendLogDao;
    private final RedisUtils redisUtils;

    @Override
    public List<LessonAttendLogVO> list(LessonAttendLogQuery query) {
        List<LessonAttendLogEntity> list=null;
        list= (List<LessonAttendLogEntity>) redisUtils.get(RedisKeys.getLessonAttendLog(query.getLessonId()),RedisUtils.MIN_TEN_EXPIRE);
        if(list==null){
            LambdaQueryWrapper<LessonAttendLogEntity> wrapper = Wrappers.lambdaQuery();
            wrapper.eq(true, LessonAttendLogEntity::getLessonId, query.getLessonId());
            list = baseMapper.selectList(wrapper);
            redisUtils.set(RedisKeys.getLessonAttendLog(query.getLessonId()),list,RedisUtils.MIN_TEN_EXPIRE);
        }
        return LessonAttendLogConvert.INSTANCE.convertList(list);
    }


    //名单校验加签到
    @Override
    public Result attendance(Long userId, Long lessonId) {
        List<LessonAttendLogVO> userList=list(new LessonAttendLogQuery(lessonId));
        if(!CollectionUtil.isEmpty(userList)){
            for (LessonAttendLogVO vo:userList){
                if(vo.getStuId()==userId){
                    if(vo.getStatus()!=1){
                        vo.setStatus(1);
                        vo.setCheckinTime(new Date());
                        LessonAttendLogEntity entity = LessonAttendLogConvert.INSTANCE.convert(vo);
                        baseMapper.insert(entity);
                        redisUtils.set(RedisKeys.getLessonAttendLog(lessonId),userList,RedisUtils.MIN_TEN_EXPIRE);

                    }
                    return Result.ok();
                }
            }
        }
        return  Result.error("不在该课堂中，不可进入此班级");
    }


    @Override
    public void save(LessonAttendLogVO vo) {
        LessonAttendLogEntity entity = LessonAttendLogConvert.INSTANCE.convert(vo);
        baseMapper.insert(entity);
    }

    @Override
    public void update(LessonAttendLogVO vo) {
        LessonAttendLogEntity entity = LessonAttendLogConvert.INSTANCE.convert(vo);
        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    public void copyUserFromClassUser(Long classId,Long lessonId) {
        List<Long> list=eduTeachApi.list(classId).getData();
        if(!CollectionUtil.isEmpty(list)){
            lessonAttendLogDao.insertUserList(list,lessonId);
        }
        redisUtils.del(RedisKeys.getLessonAttendLog(lessonId));

    }

}