package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.TeachAuditionRecordConvert;
import net.edu.module.dao.TeachAuditionRecordDao;
import net.edu.module.entity.TeachAuditionRecordEntity;
import net.edu.framework.common.page.PageResult;
import net.edu.module.query.TeachAuditionRecordQuery;
import net.edu.module.service.TeachAuditionRecordService;
import net.edu.module.vo.TeachAuditionRecordVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 教学试听安排
 *
 * @author sqw 
 * @since 1.0.0 2023-02-13
 */
@Service
@AllArgsConstructor
public class TeachAuditionRecordServiceImpl extends BaseServiceImpl<TeachAuditionRecordDao, TeachAuditionRecordEntity> implements TeachAuditionRecordService {

    @Override
    public PageResult<TeachAuditionRecordVO> page(TeachAuditionRecordQuery query) {
        IPage<TeachAuditionRecordEntity> page = baseMapper.selectAuditionRecordPage(getPage(query), query);

        return new PageResult<>(TeachAuditionRecordConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }



    @Override
    public PageResult<TeachAuditionRecordVO> newStudentAuditionRecordPage(TeachAuditionRecordQuery query) {
        IPage<TeachAuditionRecordVO> page = baseMapper.selectNewStudentAuditionRecordPage(getPage(query), query);

        return new PageResult<>(page.getRecords(), page.getTotal());
    }

    private LambdaQueryWrapper<TeachAuditionRecordEntity> getWrapper(TeachAuditionRecordQuery query){
        LambdaQueryWrapper<TeachAuditionRecordEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(TeachAuditionRecordVO vo) {
        TeachAuditionRecordEntity entity = TeachAuditionRecordConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(TeachAuditionRecordVO vo) {
        TeachAuditionRecordEntity entity = TeachAuditionRecordConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void joinAuditionLesson(TeachAuditionRecordVO vo) {
        //加入课堂签到表
        baseMapper.insertLessonAttendLog(vo);
        //判断是否已有试听记录
        if(vo.getId()!=null){
            //更新试听记录
            update(vo);
        }else{
            //保存试听记录
            save(vo);
        }

    }

//    @Override
//    public void saveNotLesson(TeachAuditionRecordVO vo) {
//        baseMapper.insertNotLessonRecord(vo);
//    }

}