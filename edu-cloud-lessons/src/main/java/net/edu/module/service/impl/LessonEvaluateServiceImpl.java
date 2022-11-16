package net.edu.module.service.impl;


import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.api.EduJudgeApi;
import net.edu.module.api.EduWxApi;
import net.edu.module.dao.LessonEvaluateDao;
import net.edu.module.entity.LessonEvaluateEntity;
import net.edu.module.service.LessonEvaluateService;
import net.edu.module.vo.LessonEvaluateVO;
import net.edu.module.vo.LessonProblemRankVO;
import net.edu.module.vo.WxLessonEvaluationVO;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;

/**
 * 课堂评价
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-18
 */
@Service
@AllArgsConstructor
public class LessonEvaluateServiceImpl extends BaseServiceImpl<LessonEvaluateDao, LessonEvaluateEntity> implements LessonEvaluateService {

    EduWxApi eduWxApi;
    EduJudgeApi eduJudgeApi;
    private final LessonEvaluateDao lessonEvaluateDao;

    @Override
    public List<LessonEvaluateVO> list(Long lessonId) {
        return baseMapper.list(lessonId);
    }

    @Override
    public void generate(Long lessonId , Integer excellent , Integer medium , Integer fail) {
        List<LessonEvaluateVO> list = lessonEvaluateDao.list(lessonId);
        //获取上节课的课堂id
        Integer lastLessonId =  lessonEvaluateDao.selectLastLessonId(lessonId.intValue());
        List<LessonProblemRankVO> lastHomework = null;
        //根据本节课的课堂ID获取本次课后作业
        List<LessonProblemRankVO> currentHomework = eduJudgeApi.getLessonProblemRank(lessonId , 2).getData();
        //根据上节课的课堂ID获取上次课后作业
        if(lastLessonId!=null){
            lastHomework = eduJudgeApi.getLessonProblemRank(lastLessonId.longValue() , 2).getData();
        }
        DecimalFormat df = new DecimalFormat("#.00");
        int total = list.size() + 1;
        double preExcellent = total * excellent * 0.01;
        double preMedium = preExcellent + total * medium * 0.01;
        double preFail = preMedium + total * fail * 0.01;
        for(int i = 0; i < list.size(); i++){

            LessonEvaluateVO vo = list.get(i);
            //获取本次课堂练习的正确数量
            Integer correctNum = vo.getCorrectNum();
            //获取本次课堂练习的总数量
            Integer totalNum = vo.getAnsweredNum() + vo.getUnansweredNum() ;
            //获取正确率
            Double perCorrect = (correctNum * 1.0 / totalNum) * 100;
            String content="本次课堂完成了" + vo.getAnsweredNum() + "题，正确率为" +df.format(perCorrect) + "%.请继续努力！";

            //排名情况
            //上中下
            if(vo.getRankNum() < preExcellent){
                content += "最近状态位于班级上游，表现优秀，继续保持哦！";
            }else if(vo.getRankNum() < preMedium){
                content += "最近状态位于班级中游，表现平平，期待你后面亮眼的表现哦！";
            }else if(vo.getRankNum() < preFail){
                content += "最近状态位于班级下游，表现不是很好，需要再加把劲啊！";
            }

            //找当前学生上次作业情况
            if(CollUtil.isNotEmpty(lastHomework)){
                for (int j=0;j<lastHomework.size();j++){
                    if(Objects.equals(lastHomework.get(j).getUserId(), vo.getUserId())){
                        content+="上次作业还有" +lastHomework.get(i).getUnansweredNum()+"题未完成,";
                    }
                }
            }
            //找当前学生本次作业量
            if(CollUtil.isNotEmpty(currentHomework)) {
                for (int j = 0; j < currentHomework.size(); j++) {
                    if (Objects.equals(currentHomework.get(j).getUserId(), vo.getUserId())) {
                        content += "本次课作业布置了" + currentHomework.get(i).getUnansweredNum() + "题,请按时完成！";
                    }
                }
            }

            list.get(i).setContent(content);
        }
        baseMapper.generate(list);
    }

    @Override
    public void update(LessonEvaluateVO vo) {
        baseMapper.updateByUserId(vo);
    }


    @Override
    public void sendEvaluate(Long lessonId){
        List<WxLessonEvaluationVO> list =baseMapper.selectEvaluate(lessonId);
        eduWxApi.insertLessonEvaluationTemplate(list);

    }
}