package net.edu.module.service.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.ArchiveScoreBookConvert;
import net.edu.module.dao.*;
import net.edu.module.entity.ArchiveGoalScoreEntity;
import net.edu.module.query.ArchiveScoreBookQuery;
import net.edu.module.service.ArchiveScoreBookService;
import net.edu.module.utils.CalculateProportionUtil;
import net.edu.module.utils.WordUtil;
import net.edu.module.vo.*;
import net.maku.entity.ArchiveScoreBookEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 记分册
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-11-22
 */
@Service
@AllArgsConstructor
public class ArchiveScoreBookServiceImpl extends BaseServiceImpl<ArchiveScoreBookDao, ArchiveScoreBookEntity> implements ArchiveScoreBookService {

    @Autowired
    private ArchiveScoreBookDao archiveScoreBookDao;

    @Autowired
    private ArchiveSignDao archiveSignDao;
    @Autowired
    private ArchiveGoalScoreDao archiveGoalScoreDao;

    @Autowired
    private ArchiveAssessDao archiveAssessDao;

    @Autowired
    private ArchiveAssessScoreDao archiveAssessScoreDao;

    @Autowired
    private ArchiveTestScoreDao archiveTestScoreDao;

    @Autowired
    private ArchiveCourseSummaryDao archiveCourseSummaryDao;


    @Override
    public PageResult<ArchiveScoreBookVO> page(ArchiveScoreBookQuery query) {
        IPage<ArchiveScoreBookEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(ArchiveScoreBookConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private LambdaQueryWrapper<ArchiveScoreBookEntity> getWrapper(ArchiveScoreBookQuery query){
        LambdaQueryWrapper<ArchiveScoreBookEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(ArchiveScoreBookVO vo) {
        ArchiveScoreBookEntity entity = ArchiveScoreBookConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(ArchiveScoreBookVO vo) {
        ArchiveScoreBookEntity entity = ArchiveScoreBookConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    public void  InsertClassInfo(ArchiveScoreBookClassInfoVO vo){
        archiveScoreBookDao.InsertClassInfo(vo);

    }


    @Override
    public List<ArchiveScoreBookClassTableVO> getClassTable(String id){

        List<ArchiveScoreBookVO> list=archiveScoreBookDao.selectList(id);
        JSONArray jsonArray = JSONUtil.parseArray(list.get(0).getClassSchedule());
        List<ArchiveScoreBookClassTableVO> archiveScoreBookClassTableVOList=new ArrayList<>();
        for(int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject=JSONUtil.parseObj(jsonArray.get(i));
            ArchiveScoreBookClassTableVO archiveScoreBookClassTableVO=new ArchiveScoreBookClassTableVO();
            archiveScoreBookClassTableVO.setId(String.valueOf(i));
            archiveScoreBookClassTableVO.setTime(String.valueOf(jsonObject.get("time")));
            archiveScoreBookClassTableVO.setPlace(String.valueOf(jsonObject.get("place")));
            archiveScoreBookClassTableVOList.add(archiveScoreBookClassTableVO);
        }
        return archiveScoreBookClassTableVOList;
    }

    @Override
    public void deleteClassTable(String id,String deleteId){
        List<ArchiveScoreBookVO> list=archiveScoreBookDao.selectList(id);
        JSONArray jsonArray=JSONUtil.parseArray(list.get(0).getClassSchedule());
        jsonArray.remove(Integer.parseInt(deleteId));
        String  classSchedule=JSONUtil.toJsonStr(jsonArray);
        archiveScoreBookDao.updateByDeleteId(id,classSchedule);
    }

    @Override
    public void  updateClassTable(String id,Object dataForm){
        String classSchedule=dataForm.toString();
        archiveScoreBookDao.updateByDeleteId(id,classSchedule);
    }

    @Override
    public List<ArchiveScoreInBookVO> getScoreListInBook(JSONObject classInfo, String id){
        List<ArchiveScoreInBookVO> list=new ArrayList<>();
        String courseId= String.valueOf(classInfo.get("courseId"));
        String summaryId=String.valueOf(classInfo.get("summaryId"));
        List<ArchiveSignVO> archiveSignVO= archiveSignDao.getSignByBookId(id);
        int i=0;
        for(ArchiveSignVO archiveSignVO1:archiveSignVO) {
            ArchiveScoreInBookVO archiveScoreInBookVO = new ArchiveScoreInBookVO();
            archiveScoreInBookVO.setId(i);
            archiveScoreInBookVO.setStuId(archiveSignVO1.getStuId());
            archiveScoreInBookVO.setStuName(archiveSignVO1.getStuName());
            archiveScoreInBookVO.setFinalScoreList(archiveCourseSummaryDao.selectFinalScore(archiveSignVO1.getStuId(), summaryId));
            archiveScoreInBookVO.setPeaceScoreList(archiveCourseSummaryDao.selectPeaceScore(archiveSignVO1.getStuId() , summaryId));
            if(archiveScoreInBookVO.getPeaceScoreList()==null){
                archiveScoreInBookVO.setPeaceScore(null);
            }else{
                BigDecimal score = archiveScoreInBookVO.getPeaceScoreList().get(0).getWeight().multiply(archiveScoreInBookVO.getPeaceScoreList().get(0).getAssessScore());
                archiveScoreInBookVO.setPeaceScore(score.toString());
            }
            if(archiveScoreInBookVO.getFinalScoreList()==null){
                archiveScoreInBookVO.setFinalScore(null);
            }else{
                    BigDecimal score1 = archiveScoreInBookVO.getFinalScoreList().get(0).getWeight().multiply(archiveScoreInBookVO.getFinalScoreList().get(0).getAssessScore());
                    archiveScoreInBookVO.setFinalScore(score1.toString());
            }


            archiveScoreInBookVO.setTotalScore(archiveGoalScoreDao.selectScoreByStudentId(summaryId,courseId,archiveSignVO1.getStuId()));
//            archiveScoreInBookVO.setAssessList(archiveAssessScoreDao.selectAssessByIds(courseId, archiveSignVO1.getStuId()));
            archiveScoreInBookVO.setTestList(archiveTestScoreDao.selectTestInfoByIds(courseId, archiveSignVO1.getStuId()));
            //评测点权重得出期末等分数写法（暂不使用）
//            archiveScoreInBookVO.setAssessList(archiveTestScoreDao.selectAssessInfoByIds(courseId, archiveSignVO1.getStuId()));

            i++;
            list.add(archiveScoreInBookVO);
        }

        return list;
    }

    @Override
    public void addTeachNotes(String dataForm,String bookId){

        archiveScoreBookDao.updateTeachNotes(dataForm,bookId);
    }

    @Override
    public void addAnswerNotes(String dataForm,String bookId){

        archiveScoreBookDao.updateAnswerNotes(dataForm,bookId);
    }

    @Override
    public void createScoreBookWord(Long bookId, HttpServletResponse response) throws IOException {
        System.out.println(bookId);
        WordUtil.createScoreBookWord(response);
    }

    @Override
    public ArchiveScoreBookVO selectScoreBookById(Long id) {
        return archiveScoreBookDao.selectScoreBookById(id);
    }
}
