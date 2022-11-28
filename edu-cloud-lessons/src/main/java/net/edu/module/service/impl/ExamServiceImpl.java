package net.edu.module.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.edu.framework.common.cache.RedisKeys;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.RedisUtils;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.framework.security.user.SecurityUser;
import net.edu.module.api.EduJudgeApi;
import net.edu.module.api.EduWxApi;
import net.edu.module.convert.ExamConvert;
import net.edu.module.dao.ExamDao;
import net.edu.module.entity.ExamEntity;
import net.edu.module.query.ExamQuery;
import net.edu.module.service.ExamAttendLogService;
import net.edu.module.service.ExamProblemService;
import net.edu.module.service.ExamService;
import net.edu.module.utils.ExamExcelUtil;
import net.edu.module.utils.ExamProblemInfoExcelUtil;
import net.edu.module.vo.*;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 考试
 *
 * @author 小樊 babamu@126.com
 * @since 1.0.0 2022-10-09
 */
@Service
@AllArgsConstructor
@Slf4j
public class ExamServiceImpl extends BaseServiceImpl<ExamDao, ExamEntity> implements ExamService {

    private final ExamAttendLogService examAttendLogService;

    private final ExamProblemService examProblemService;

    private final EduJudgeApi eduJudgeApi;

    private final EduWxApi eduWxApi;

    private final RedisUtils redisUtils;

    private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public PageResult<ExamVO> page(ExamQuery query) {
        query.setTeacherId(SecurityUser.getUserId());
        Page<ExamVO> page = new Page<>(query.getPage(),query.getLimit());
        IPage<ExamVO> list = baseMapper.page(page,query);
        return new PageResult<>(list.getRecords(), list.getTotal());
    }


    @Override
    public ExamEntity get(Long examId) {
        return baseMapper.selectById(examId);
    }



    @Override
    public PageResult<ExamVO> studentPage(ExamQuery query){
        Page<ExamVO> page = new Page<>(query.getPage(), query.getLimit());
        IPage<ExamVO> list = baseMapper.studentPage(page, query);
        for (int i=0;i<list.getRecords().size();i++){
            list.getRecords().get(i).setClassListName(baseMapper.selectExamClass(list.getRecords().get(i).getId()));
        }
        return new PageResult<>(list.getRecords(), list.getTotal());
    }

    @Override
    public List<ExamVO> getExamingList(Long userId){
        return baseMapper.getExamingList(userId);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ExamVO vo) {

        baseMapper.insertExam(vo);
        for (int i =0;i<vo.getClassIdList().size();i++){
            baseMapper.insertExamClass(vo.getId(),vo.getClassIdList().get(i));
        }

        //插入题目
        examProblemService.copyFromPaper(vo.getPaperId(),vo.getId());

        //插入名单
        examAttendLogService.copyFromClass(vo.getClassIdList(),vo.getId());

        //异步线程推送至微信
        threadPoolTaskExecutor.submit(new Thread(()->{
            List<WxExamArrangementVO> list = baseMapper.selectExamArrangement(vo);
            eduWxApi.insertExamArrangementTemplate(list);
        }));


    }

    @Override
    public void update(ExamVO vo) {
        ExamEntity entity = ExamConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }


    @Override
    public void updateExamIndex(Long examId) {
        Long userId = SecurityUser.getUserId();
        ExamPaperVo vo= (ExamPaperVo) redisUtils.get(RedisKeys.getStuExam(examId,userId));
        if (vo.getPaperProblem().size() >vo.getProblemIndex()){
            vo.setProblemIndex(vo.getProblemIndex()+1);
            Long time=vo.getAttendLogVO().getFinishExamTime().getTime()-System.currentTimeMillis()+5000L;
            redisUtils.set(RedisKeys.getStuExam(examId,userId),vo,time/1000);
        }



    }

    @Override
    public void submitPaper(Long examId,Long userId) {
        examAttendLogService.updateExamStatus(2,examId,userId,new Date());
        redisUtils.del(RedisKeys.getStuExam(examId,userId));

        threadPoolTaskExecutor.submit(new Thread(()->{
            //自动批卷
            eduJudgeApi.makePaper(examId,userId);
            //获取分数
            ExamScoreVO vo=  eduJudgeApi.getUserExamScore(examId,userId).getData();
            BigDecimal score=new BigDecimal(0);
            for (ExamProblemRecord item:vo.getProblemRecords()){
                score=score.add(item.getFraction());
            }
            //更新分数
            ExamAttendLogVO attendLogVO=new ExamAttendLogVO();
            attendLogVO.setExamId(examId);
            attendLogVO.setUserId(userId);
            attendLogVO.setScore(score);
            attendLogVO.setIsCorrecting(0);
            examAttendLogService.updateAttendLogScore(attendLogVO);
        }));
    }

    @Override
    public void exportExam(Long examId, HttpServletResponse response) throws IOException {
        //查询学生考试情况数据
        List<ExamScoreVO> data =  eduJudgeApi.getExamRecordList(examId).getData();
        //遍历考试题目
        List<String> header = new ArrayList<>();
        for (int j = 0;j<data.get(0).getProblemRecords().size();j++){
            header.add(j+1+"、"+data.get(0).getProblemRecords().get(j).getProblemName());
        }
        //设置excel大表头
        ExamEntity entity =baseMapper.selectById(examId);
        String bigTitle = "《"+entity.getName()+"》"+"\r\n"+ " 总分："+entity.getScore()+"\r\n"+"("+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(entity.getBeginTime()) +"-"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(entity.getEndTime())+")";
        //传入题目，考试数据生成excel
        ExamExcelUtil.examExportExcel(header,data,bigTitle,response);
    }

    @Override
    public void exportUserExam(Long examId, List<Long> userIdList,HttpServletResponse response) throws IOException {

        List<ExamUserExcelVo> data = eduJudgeApi.getExamProblemInfoList(examId,userIdList).getData();
        List<String> bigTitleList =new ArrayList<>();
        ExamEntity entity =baseMapper.selectById(examId);
        for (int i = 0 ; i<data.size();i++){
            BigDecimal sum = new BigDecimal("0.00");
            for (int j = 0 ; j<data.get(i).getProblemInfoList().size();j++){
                sum = sum.add(data.get(i).getProblemInfoList().get(j).getFraction());
            }
            bigTitleList.add("《"+entity.getName()+"》"+"姓名："+data.get(i).getName()+"\r\n"+ " 总分："+entity.getScore()+" 得分："+sum+""+"\r\n"+"("+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(entity.getBeginTime()) +"-"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(entity.getEndTime())+")");
        }
        ExamProblemInfoExcelUtil.examExportExcel(data,bigTitleList,response);
    }

    @Override
    public ExamAbilityVo getAbilityExam() {
        ExamAbilityVo examAbilityVo = new ExamAbilityVo();
        examAbilityVo.setExamAbilityList(baseMapper.selectAbilityExam());
        examAbilityVo.setExamIdList(baseMapper.selectAbilityExamId(SecurityUser.getUserId()));
        return examAbilityVo;
    }

    @Override
    public void promulgateGrade(Long examId, Long abilityId, Integer score) {
        baseMapper.updateUserAbilityId(examId, abilityId, score);
    }
}
