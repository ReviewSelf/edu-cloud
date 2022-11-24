package net.edu.module.service.impl;


import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.entity.ArchiveAssessEntity;
import net.edu.module.query.ArchiveAssessQuery;
import net.edu.module.service.ArchiveWeightTargetAssessService;
import net.edu.module.vo.*;
import net.edu.module.dao.ArchiveAssessDao;
import net.edu.module.service.ArchiveAssessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.ArrayList;
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
    public void deleteAssess(Long courseId, Long  targetId,Long assessId) {
        archiveAssessDao.deleteAssess(courseId,targetId,assessId);
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



    @SneakyThrows
    @Override
    public void assessFromExcel(MultipartFile file) {
        List<Long> list1=new ArrayList<>();
        list1.add(2L);
        List<ArchiveAssessExcelVO> list= EasyExcel.read(file.getInputStream()).head(ArchiveAssessExcelVO.class).sheet().headRowNumber(3).doReadSync();
        for (ArchiveAssessExcelVO vo:list){


        }
    }

    @Override
    public ArchiveAssessVO getSummaryStep2(String courseId) {
        return archiveAssessDao.selectSummaryStep2(courseId);
    }

    @Override
    public List<ArchiveAssessByCourseIdVo> getAssessByCourseId(String courseId) {
        return archiveAssessDao.selectAssessBycourseId(courseId);
    }

    @Override
    public void deleteByCourseId(String courseId, String assessId) {
        Integer cid = Integer.parseInt(courseId);
        Integer ass = Integer.parseInt(assessId);
        archiveAssessDao.updateByCourseId(cid , ass);
    }

    @Override
    public void saveAssessWeight(List<ArchiveAssessByCourseIdVo> assess) {
        String flag;
        for(int i = 0 ; i < assess.size() ; i++) {
            flag = archiveAssessDao.selectArchiveAssessIdJuge(assess.get(i).getAssessId() , assess.get(i).getTargetId());
            if(flag == null) {
                archiveAssessDao.insertAssessWeight(assess.get(i));
            } else {
                archiveAssessDao.updateAssessWeight(assess.get(i));
            }
        }
    }

    @Override
    public List<ArchiveAssessByCourseIdVo> getAssessByTargetId(String targetId) {
        return archiveAssessDao.selectAssessByTargetId(targetId);
    }

    @Override
    public void saveEvaluation(ArchivePointAndTargetVO assess) {
        archiveAssessDao.updateEvaluation(assess);
    }

    @Override
    public BigDecimal getWeightSum(ArchiveAssessByCourseIdVo assess) {
        return archiveAssessDao.selectWeightSum(assess);
    }

//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public Long save(ArchiveAssessExcelVO vo) {
////        UserEntity entity = UserConvert.INSTANCE.convert(vo);
////
////        // 判断用户名是否存在
////
////
////        // 保存用户
////        baseMapper.insert(entity);
////
////        // 保存用户角色关系
////        userRoleService.saveOrUpdate(entity.getId(), vo.getRoleIdList());
////
////        return entity.getId();
//
//    }



}
