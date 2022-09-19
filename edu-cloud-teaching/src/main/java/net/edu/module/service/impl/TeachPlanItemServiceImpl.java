package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.TeachPlanItemConvert;
import net.edu.module.dao.TeachPlanDao;
import net.edu.module.dao.TeachPlanItemDao;
import net.edu.module.dao.TeachPlanItemPaperDao;
import net.edu.module.dao.TeachPlanItemResourceDao;
import net.edu.module.entity.TeachPlanItemEntity;
import net.edu.module.query.TeachPlanItemQuery;
import net.edu.module.service.TeachPlanItemService;
import net.edu.module.service.TeachPlanService;
import net.edu.module.vo.TeachPlanItemPaperVO;
import net.edu.module.vo.TeachPlanItemResourceVO;
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
    private final TeachPlanService teachPlanService;
    private final TeachPlanItemPaperDao teachPlanItemPaperDao;
    private final TeachPlanItemResourceDao teachPlanItemResourceDao;


    @Override
    public List<TeachPlanItemVO> list(Long id) {
        List<TeachPlanItemVO> list = teachPlanItemDao.list(id);
        return list;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(TeachPlanItemVO vo) {
        TeachPlanItemEntity entity = TeachPlanItemConvert.INSTANCE.convert(vo);
        baseMapper.insert(entity);//新增教学日历
        System.out.println(entity.getId());
        if (vo.getPaperList().size() > 0) {
            teachPlanItemPaperDao.insertItemPaper(vo.getPaperList(), entity.getId()); //新增日历试卷
        }
        teachPlanService.updateLessonNum(entity.getPlanId());//更新教学计划的课次（日历数）
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(TeachPlanItemVO vo) {
        TeachPlanItemEntity entity = TeachPlanItemConvert.INSTANCE.convert(vo);

        updateById(entity);

        teachPlanService.updateLessonNum(entity.getPlanId());//更新教学计划的课次
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        TeachPlanItemVO vo = teachPlanItemDao.selectPlanItem(idList.get(0)); //获取日历所属计划的id信息
        removeByIds(idList);//真正的删除操作
        teachPlanService.updateLessonNum(vo.getPlanId());//更新教学计划的课次（日历数）
    }

    @Override
    public List<TeachPlanItemPaperVO> getItemPaper(Long id) {
        return teachPlanItemPaperDao.selectItemPaper(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateItemPaper(List<TeachPlanItemPaperVO> list) {
        //删除当前教学日历中老的试卷
        teachPlanItemPaperDao.deleteItemPaper(list.get(0).getItemId());
        //插入试卷到当前教学日历中
        teachPlanItemPaperDao.insertItemPaper(list, list.get(0).getItemId());
    }

    @Override
    public List<TeachPlanItemResourceVO> getItemResource(Long id) {
        return teachPlanItemResourceDao.selectItemResource(id);
    }

    @Override
    public void deleteItemResource(Long id) {
        teachPlanItemResourceDao.deletedItemResource(id);
    }

    @Override
    public void saveItemResource(TeachPlanItemResourceVO vo) {
        teachPlanItemResourceDao.insertItemResource(vo);
    }

}
