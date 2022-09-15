package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.TeachPlanItemConvert;
import net.edu.module.dao.TeachPlanDao;
import net.edu.module.dao.TeachPlanItemDao;
import net.edu.module.dao.TeachPlanItemPaperDao;
import net.edu.module.entity.TeachPlanItemEntity;
import net.edu.module.query.TeachPlanItemQuery;
import net.edu.module.service.TeachPlanItemService;
import net.edu.module.vo.TeachPlanItemPaperVO;
import net.edu.module.vo.TeachPlanItemVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 教学日历表
 *
 * @author sqw 
 * @since 1.0.0 2022-09-12
 */
@Service
@AllArgsConstructor
public class TeachPlanItemServiceImpl extends BaseServiceImpl<TeachPlanItemDao, TeachPlanItemEntity> implements TeachPlanItemService {

    private final TeachPlanItemDao teachPlanItemDao;
    private final TeachPlanDao teachPlanDao;
    private final TeachPlanItemPaperDao teachPlanItemPaperDao;

    @Override
    public List<TeachPlanItemVO> page( Long id) {
        List<TeachPlanItemVO> list = teachPlanItemDao.page(id);
        return  list;
    }

    private LambdaQueryWrapper<TeachPlanItemEntity> getWrapper(TeachPlanItemQuery query){
        LambdaQueryWrapper<TeachPlanItemEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(TeachPlanItemVO vo) {
        TeachPlanItemEntity entity = TeachPlanItemConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
        teachPlanDao.updateLessonNum(entity.getPlanId());//更新教学计划的课次
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(TeachPlanItemVO vo) {
        TeachPlanItemEntity entity = TeachPlanItemConvert.INSTANCE.convert(vo);

        updateById(entity);

        teachPlanDao.updateLessonNum(entity.getPlanId());//更新教学计划的课次
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        TeachPlanItemVO vo = teachPlanItemDao.selectPlanItem(idList.get(0)); //获取日历所属计划的id信息
        removeByIds(idList);//真正的删除操作
        teachPlanDao.updateLessonNum(vo.getPlanId());//更新教学计划的课次
    }

    @Override
    public List<TeachPlanItemPaperVO> getItemPaper(Long id) {
        return teachPlanItemPaperDao.selectItemPaper(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateItemPaper(List<TeachPlanItemPaperVO> list) {
        teachPlanItemPaperDao.deleteItemPaper(list.get(0).getItemId());//删除当前教学日历中老的试卷
        teachPlanItemPaperDao.insertItemPaper(list);//插入试卷到当前教学日历中
    }

}