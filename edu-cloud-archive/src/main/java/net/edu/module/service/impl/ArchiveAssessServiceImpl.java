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


    private LambdaQueryWrapper<ArchiveAssessEntity> getWrapper(ArchiveAssessQuery query){
        LambdaQueryWrapper<ArchiveAssessEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(ArchiveWeightTargetAssessVO vo) {

        ArchiveAssessEntity entity=new ArchiveAssessEntity();
//        ArchiveAssessVO assessVO = new ArchiveAssessVO();
        entity.setName(vo.getAssessName());
        System.out.println("新增前"+entity);
        archiveAssessDao.insertArchiveAccess1(entity);
        System.out.println("新增后"+entity);
        vo.setAssessId(entity.getId());
        archiveWeightTargetAssessService.insertArchiveAccess2(vo);
    }

    @Override
    public void update(ArchiveAssessVO vo) {
        ArchiveAssessEntity entity = ArchiveAssessConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }



}
