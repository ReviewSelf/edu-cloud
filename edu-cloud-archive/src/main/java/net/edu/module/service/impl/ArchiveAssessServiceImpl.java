package net.edu.module.service.impl;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.ArchiveAssessConvert;
import net.edu.module.dao.ArchiveWeightTargetAssessDao;
import net.edu.module.entity.ArchiveAssessEntity;
import net.edu.module.query.ArchiveAssessQuery;
import net.edu.module.service.ArchiveWeightTargetAssessService;
import net.edu.module.vo.ArchiveAssessVO;
import net.edu.module.dao.ArchiveAssessDao;
import net.edu.module.service.ArchiveAssessService;
import net.edu.module.vo.ArchiveCourseVO;
import net.edu.module.vo.ArchiveWeightTargetAssessVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 考核点
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-26
 */
@Service
@AllArgsConstructor
public class ArchiveAssessServiceImpl extends BaseServiceImpl<ArchiveAssessDao, ArchiveAssessEntity> implements ArchiveAssessService {

    @Autowired
    private ArchiveAssessDao archiveAssessDao;

    @Autowired
    private ArchiveWeightTargetAssessService archiveWeightTargetAssessService;



    @Override
    public PageResult<ArchiveAssessVO> page(ArchiveAssessQuery query) {
        Page<ArchiveAssessVO> page = new Page<>(query.getPage(),query.getLimit());
        IPage<ArchiveAssessVO> list = archiveAssessDao.selectArchiveAssessByPage(page,query);
        return new PageResult<>(list.getRecords(), page.getTotal());
    }

    @Override
    public ArchiveAssessVO selectArchiveAssessById(Long id) {
        System.out.println(archiveAssessDao.selectArchiveAssessById(id));
        return archiveAssessDao.selectArchiveAssessById(id);
    }


    private LambdaQueryWrapper<ArchiveAssessEntity> getWrapper(ArchiveAssessQuery query){
        LambdaQueryWrapper<ArchiveAssessEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(ArchiveWeightTargetAssessVO vo) {
        ArchiveAssessEntity entity=new ArchiveAssessEntity();
//        ArchiveAssessVO assessVO = new ArchiveAssessVO();
        entity.setName(vo.getAssessName());
        archiveAssessDao.insertArchiveAccess1(entity);

        vo.setAssessId(entity.getId());
        archiveWeightTargetAssessService.insertArchiveAccess2(vo);
    }

    @Override
    public void update(ArchiveAssessVO vo) {
        System.out.println(vo);
        archiveAssessDao.updateArchiveAssess1(vo);
        archiveAssessDao.updateArchiveAssess2(vo);
    }

    @Override
    public void save1(ArchiveAssessVO vo) {
        archiveAssessDao.insertArchiveAssess1(vo);
    }

    @Override
    public void update1(ArchiveAssessVO vo) {
        archiveAssessDao.updateArchiveAssess3(vo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
        archiveWeightTargetAssessService.delete(idList);

        for(int i=0;i<idList.size();i++){
            archiveWeightTargetAssessService.deleteByAssessId(idList.get(i));
        }

    }

    @Override
    public List<ArchiveAssessVO> selectAssessByCourseId(Long courseId) {
        return archiveAssessDao.selectAssessByCourseId(courseId);
    }




}
