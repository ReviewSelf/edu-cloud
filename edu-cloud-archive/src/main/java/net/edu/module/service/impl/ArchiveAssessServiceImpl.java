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
    public void deleteAssess(Long id) {
        archiveAssessDao.deleteAssess(id);
    }

    @Override
    public BigDecimal getMannerWeight(String courseId) {
        BigDecimal sum = archiveAssessDao.selectMannerWeight(courseId);
        if(sum != null) {
            BigDecimal Bsum = sum.setScale(2);
            BigDecimal One = new BigDecimal(1.00);
            return One.subtract(Bsum);
        } else
            return null;
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
        System.out.println(assessId);
        Integer cid = Integer.parseInt(courseId);
        Integer ass = Integer.parseInt(assessId);
        archiveAssessDao.updateByCourseId(cid , ass);
        archiveAssessDao.updateArchiveManner(cid , ass);
    }

    @Override
    public void saveAssessWeight(List<ArchiveAssessByCourseIdVo> assess) {
        System.out.println(assess);
        String flag;
        String mannerFlag;
        for(int i = 0 ; i < assess.size() ; i++) {
            Integer mannerId = assess.get(i).getMannerId();
                    flag = archiveAssessDao.selectArchiveAssessIdJuge(assess.get(i).getAssessId() , assess.get(i).getTargetId());
            mannerFlag = archiveAssessDao.selectArchiveMannerJuge(assess.get(i).getAssessId() , assess.get(i).getTargetId());
            if(mannerFlag == null) {
                ArchiveAssessByCourseIdVo as = assess.get(i);
                archiveAssessDao.insertAssessManner(as);
                mannerId = as.getId();
                        System.out.println(mannerId);
            } else {
                archiveAssessDao.updateAssessManner(assess.get(i));
            }
            if(flag == null) {
                assess.get(i).setMannerId(mannerId);
                archiveAssessDao.insertAssessWeight(assess.get(i));
                System.out.println("xxx");
                System.out.println(assess.get(i));

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
    @Override
    public ArchiveAssessTableVo getWeightTableStep4(Long courseId) {
        Integer TargetNum = archiveAssessDao.selectTargetByCourseIdStep4(courseId);
        Integer AssessNum = archiveAssessDao.selectAssessNumStep4(courseId);
        List<String> AssessName = archiveAssessDao.selectAssessNameStep4(courseId);
        List<BigDecimal> TargetWeightArr = archiveAssessDao.selectTargetWeightArrStep4(courseId);
        BigDecimal[] TargetArr = new BigDecimal[TargetNum];
        BigDecimal m = new BigDecimal(100);
        for(int i = 0 ; i < TargetWeightArr.size() ; i++) {
            TargetArr[i] = TargetWeightArr.get(i).multiply(m);
        }
        List<String> TargetName = archiveAssessDao.selectTargetNameStep4(courseId);
        System.out.println(TargetName);

        //第四步，获取考核点在该教学目标下的占比
        BigDecimal[][] AssessWeightArr = new BigDecimal[TargetNum][AssessNum];
        List<Integer>  TargetIdArr;
        TargetIdArr = archiveAssessDao.selectTargetIdStep4(courseId);
        List<Integer> AssessIdArr;
        AssessIdArr = archiveAssessDao.selectAssessIdStep4(courseId);
        for(int i = 0 ; i < TargetNum ; i++) {
            for (int j = 0 ; j < AssessNum ; j++) {
                AssessWeightArr[i][j] = archiveAssessDao.selectWeight(TargetIdArr.get(i) , AssessIdArr.get(j));
                if(AssessWeightArr[i][j] == null) {
                    AssessWeightArr[i][j] = BigDecimal.valueOf(0);
                }
            }
        }
        ArchiveAssessTableVo assessTableVo = new ArchiveAssessTableVo();
        assessTableVo.setAssessNum(AssessNum);
        assessTableVo.setTargetNum(TargetNum);
        assessTableVo.setAssessWeightArr(AssessWeightArr);
        assessTableVo.setTargetWeightArr(TargetArr);

        String[][] outCome = new String[TargetNum + 1][AssessNum + 1];
        for(int i = -1 ; i < TargetNum ; i++) {
            for (int j = -1 ; j < AssessNum ; j++) {
                if(i == -1 && j == -1) {
                    outCome[i + 1][j + 1] = "考核点名称";
                } else if(i == -1) {
                    outCome[i + 1][j + 1] = AssessName.get(j);
                }
                if(i > -1 && j == -1) {
                    outCome[i + 1][j + 1] = TargetName.get(i) + "(" + TargetArr[i] + ")";
                } else if(i > -1 && j > -1) {
                    outCome[i + 1][j + 1] = AssessWeightArr[i][j].toString();
                }
            }
        }
        assessTableVo.setRouCome(outCome);
        return assessTableVo;
    }


    @Override
    public ArchiveAssessTableVo getWeightTable(ArchiveAssessByCourseIdVo assess) {

        //第一步，获取教学目标总数
        Integer courseId = assess.getCourseId();
        Integer TargetNum = archiveAssessDao.selectTargetByCourseId(courseId);

        //第二步，获取考核点总数和考核点名称
        Integer AssessNum = archiveAssessDao.selectAssessSum(assess);
        List<String> AssessName = archiveAssessDao.selectAssessName(courseId);
        System.out.println(AssessName);

        //第三步，获取教学目标占比数组
        List<BigDecimal> TargetWeightArr = archiveAssessDao.selectTargetWeightArr(assess);
        BigDecimal[] TargetArr = new BigDecimal[TargetNum];
        BigDecimal m = new BigDecimal(100);
        for(int i = 0 ; i < TargetWeightArr.size() ; i++) {
            TargetArr[i] = TargetWeightArr.get(i).multiply(m);
        }
        List<String> TargetName = archiveAssessDao.selectTargetName(courseId);
        System.out.println(TargetName);

        //第四步，获取考核点在该教学目标下的占比
        BigDecimal[][] AssessWeightArr = new BigDecimal[TargetNum][AssessNum];
        List<Integer>  TargetIdArr;
        TargetIdArr = archiveAssessDao.selectTargetId(courseId);
        List<Integer> AssessIdArr;
        AssessIdArr = archiveAssessDao.selectAssessId(assess);
        for(int i = 0 ; i < TargetNum ; i++) {
            for (int j = 0 ; j < AssessNum ; j++) {
                AssessWeightArr[i][j] = archiveAssessDao.selectWeight(TargetIdArr.get(i) , AssessIdArr.get(j));
                if(AssessWeightArr[i][j] == null) {
                    AssessWeightArr[i][j] = BigDecimal.valueOf(0);
                }
            }
        }
        ArchiveAssessTableVo assessTableVo = new ArchiveAssessTableVo();
        assessTableVo.setAssessNum(AssessNum);
        assessTableVo.setTargetNum(TargetNum);
        assessTableVo.setAssessWeightArr(AssessWeightArr);
        assessTableVo.setTargetWeightArr(TargetArr);

        String[][] outCome = new String[TargetNum + 1][AssessNum + 1];
        for(int i = -1 ; i < TargetNum ; i++) {
            for (int j = -1 ; j < AssessNum ; j++) {
                if(i == -1 && j == -1) {
                    outCome[i + 1][j + 1] = "考核点名称";
                } else if(i == -1) {
                    outCome[i + 1][j + 1] = AssessName.get(j);
                }
                if(i > -1 && j == -1) {
                    outCome[i + 1][j + 1] = TargetName.get(i) + "(" + TargetArr[i] + ")";
                } else if(i > -1 && j > -1) {
                    outCome[i + 1][j + 1] = AssessWeightArr[i][j].toString();
                }
            }
        }
        System.out.println(outCome);
        assessTableVo.setRouCome(outCome);
        System.out.println(assessTableVo.getRouCome());
        return assessTableVo;
    }

     @Override
     public List<ArchiveAssessVO>  selectWeightById(String id){
        return archiveAssessDao.selectWeightById(id);
     }
    @Override
    public List<ArchiveAssessScoreBookWeightList> selectWeightByIdNew(String id){

        List<ArchiveAssessScoreBookWeightList> list=archiveAssessDao.selectWeightByIdNew(id);

        if(list.size()==2){
            list.get(0).setName("平时成绩");
            list.get(1).setName("期末成绩");
        }

        return list;
    }



}
