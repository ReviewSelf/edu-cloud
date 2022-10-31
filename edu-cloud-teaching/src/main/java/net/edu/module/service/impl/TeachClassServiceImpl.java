package net.edu.module.service.impl;

import cn.hutool.core.collection.CollUtil;
import net.edu.module.api.EduLessonApi;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import net.edu.framework.common.cache.RedisKeys;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.RedisUtils;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.framework.security.user.SecurityUser;
import net.edu.module.convert.TeachClassConvert;
import net.edu.module.dao.TeachClassDao;
import net.edu.module.dao.TeachClassUserDao;
import net.edu.module.dao.TeachPlanItemDao;
import net.edu.module.entity.TeachClassEntity;
import net.edu.module.query.TeachClassQuery;
import net.edu.module.service.TeachPlanItemService;
import net.edu.module.service.TeachPlanService;
import net.edu.module.vo.TeachClassVO;
import net.edu.module.service.TeachClassService;
import net.edu.module.vo.TeachPlanItemVO;
import net.edu.module.vo.TeachPriceVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 班级表
 *
 * @author wengruichen babamu@126.com
 * @since 1.0.0 2022-09-09
 */
@Service
@AllArgsConstructor
public class TeachClassServiceImpl extends BaseServiceImpl<TeachClassDao, TeachClassEntity> implements TeachClassService {

    private final TeachClassDao teachClassDao;
    private final TeachClassUserDao teachClassUserDao;
    private final TeachPlanService teachPlanService;
    private final TeachPlanItemService teachPlanItemService;
    private final EduLessonApi eduLessonApi;

    private final RedisUtils redisUtils;


    @Override
    public PageResult<TeachClassVO> page(TeachClassQuery query) {
        Page<TeachClassVO> page = new Page<>(query.getPage(), query.getLimit());
        IPage<TeachClassVO> list = teachClassDao.page(page, query);
        return new PageResult<>(list.getRecords(), list.getTotal());
    }

    @Override
    public List<TeachPlanItemVO> selectLesson(Long id) {
        List<TeachPlanItemVO> teachPlanItemVOList = teachPlanItemService.list(id);
        return teachPlanItemVOList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(TeachClassVO vo) {
        TeachClassEntity entity = TeachClassConvert.INSTANCE.convert(vo);
        baseMapper.insert(entity);
        if(vo.getUserIdList().size()>0){
            teachClassUserDao.insertClassUser(vo.getUserIdList(), entity.getId());
        }
        teachPlanService.updateUsedNum(entity.getPlanId());
    }

    @Override
    public void update(TeachClassVO vo) {
        System.out.println(vo);
        TeachClassEntity entity = TeachClassConvert.INSTANCE.convert(vo);
        System.out.println(entity);
        updateById(entity);
        redisUtils.del(RedisKeys.getActivityClass());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
        for(int i=0;i<idList.size();i++){
            System.out.println(idList.get(i));
            System.out.println(eduLessonApi.delete(idList.get(i)));
        }
        redisUtils.del(RedisKeys.getActivityClass());
    }

//    @Override
//    public List<TeachClassVO> getById(Integer id) {
//        return teachClassDao.selectById(id);
//    }

    @Override
    public List<TeachClassVO> getClassForStudent(Integer status) {
        Long userId = SecurityUser.getUserId();
        return teachClassDao.selectClassForStudent(userId, status);
    }

    @Override
    public List<TeachClassVO> getClassForTeacher(Integer status) {
        Long userId = SecurityUser.getUserId();
        return teachClassDao.selectClassForTeacher(userId, status);
    }

    @Override
    public void updateNextLesson(Integer nextLesson, Long classId) {
        teachClassDao.updateNextLesson(nextLesson, classId);
    }

    @Override
    public void endingCalss(Integer id) {
        teachClassDao.endingClass(id);
        redisUtils.del(RedisKeys.getActivityClass());
    }

    @Override
    public TeachClassVO getClassById(Long id) {
        return teachClassDao.selectClassById(id);
    }


    @Override
    public List<TeachClassEntity> getOldClassUser(Long userId) {
        return teachClassDao.selectOldClassUser(userId);
    }


    @Override
    public List<TeachClassVO> getOpenClassesList() {
        List<TeachClassVO> activityClass=null;
        activityClass= (List<TeachClassVO>) redisUtils.get(RedisKeys.getActivityClass());
        if(CollUtil.isEmpty(activityClass)){
            activityClass= teachClassDao.selectOpenClasses();
            redisUtils.set(RedisKeys.getActivityClass(),activityClass,RedisUtils.HOUR_ONE_EXPIRE);
        }
        return activityClass;
    }
}
