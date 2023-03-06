package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.api.EduSaleApi;
import net.edu.module.convert.TeachDestroyedLessonRecordConvert;
import net.edu.module.dao.TeachDestroyedLessonRecordDao;
import net.edu.module.entity.TeachDestroyedLessonRecordEntity;
import net.edu.module.query.TeachDestroyedLessonRecordQuery;
import net.edu.module.service.TeachDestroyedLessonRecordService;
import net.edu.module.vo.TeachClassHoursVO;
import net.edu.module.vo.TeachDestroyedLessonRecordListVO;
import net.edu.module.vo.TeachDestroyedLessonRecordVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 消课管理
 *
 * @author sqw 
 * @since 1.0.0 2023-03-04
 */
@Service
@AllArgsConstructor
public class TeachDestroyedLessonRecordServiceImpl extends BaseServiceImpl<TeachDestroyedLessonRecordDao, TeachDestroyedLessonRecordEntity> implements TeachDestroyedLessonRecordService {

    private final EduSaleApi eduSaleApi;

    @Override
    public PageResult<TeachDestroyedLessonRecordVO> page(TeachDestroyedLessonRecordQuery query) {
        IPage<TeachDestroyedLessonRecordVO> page = baseMapper.selectDestroyedLessonRecordPage(getPage(query), query);

        return new PageResult<>(page.getRecords(), page.getTotal());
    }

    @Override
    public void save(TeachDestroyedLessonRecordVO vo) {
        TeachDestroyedLessonRecordEntity entity = TeachDestroyedLessonRecordConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(TeachDestroyedLessonRecordVO vo) {
        vo.setStatus(1);
        //更新消课记录表
        TeachDestroyedLessonRecordEntity entity = TeachDestroyedLessonRecordConvert.INSTANCE.convert(vo);
        updateById(entity);
        //记录课时流水表
        Result<TeachClassHoursVO> vo1 = eduSaleApi.getStudentPay(vo.getStuId()); //获取用户余额

        if(vo.getType() == 0){ //常规课

        }else if(vo.getType() == 1){

        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    public void saveList(TeachDestroyedLessonRecordListVO vo) {
        if(vo.getStuIdList()!=null && !vo.getStuIdList().isEmpty()){
            for(int i = 0;i<vo.getStuIdList().size();i++){
                vo.getVo().setStuId(vo.getStuIdList().get(i));
                save(vo.getVo());
            }
        }
    }

}