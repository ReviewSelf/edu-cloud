package net.edu.module.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import net.edu.framework.common.mq.BindingName;
import net.edu.framework.common.mq.ExchangeName;
import net.edu.framework.common.utils.EncryptUtils;
import net.edu.module.api.EduFileApi;
import net.edu.module.api.EduProblemApi;
import net.edu.module.api.EduTeachApi;
import net.edu.module.dao.JudgeRecordDao;
import net.edu.module.dao.JudgeRecordSampleDao;
import net.edu.module.vo.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/4 1:10
 * @Version: 1.0
 * @Description:
 */
@Service
@AllArgsConstructor
public class JudgeService {


    private final RabbitTemplate rabbitTemplate;

    private final EduTeachApi eduTeachApi;


    private final JudgeRecordDao judgeRecordDao;

    private final EduFileApi eduFileApi;

    private final EduProblemApi eduProblemApi;

    private final JudgeRecordSampleDao judgeRecordSampleDao;


    public void judgeBefore(JudgeRecordSubmitVO vo) {
        //插入记录
        if (vo.getProblemType() == 1) {
            //选择题直接判题
            judgeChoice(vo);
        } else if (vo.getProblemType() == 2) {
            //填空题只插入
            judgeRecordDao.insertSubmitRecord(vo);
        } else if (vo.getProblemType() == 3) {
            //代码题先插入后推送至mq
            judgeRecordDao.insertSubmitRecord(vo);
            if (!StrUtil.isEmpty(vo.getSubmitContent())) {
                rabbitTemplate.convertAndSend(ExchangeName.DEFAULT_EXCHANGE, BindingName.JUDGE_BINDING, vo.getId());
            }
            //提交到mq

        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void judgeChoice(JudgeRecordSubmitVO vo) {
        List<String> arr = Arrays.asList(vo.getSubmitContent().split(";;;"));
        List<String> answer = eduProblemApi.getChoiceOptions(vo.getProblemId(), 1).getData();
        if (arr.size() != answer.size()) {
            vo.setSubmitStatus(4);
        } else {
            vo.setSubmitStatus(3);
            for (String item : arr) {
                if (!answer.contains(item)) {
                    vo.setSubmitStatus(4);
                    break;
                }
            }
        }
        judgeRecordDao.insertSubmitRecord(vo);
        judgeAfter(vo.getId(), vo.getProblemId(), 1);
    }

    @Transactional(rollbackFor = Exception.class)
    public void judgeCode(Long recordId) {
//        获取判题内容，时间限制，空间限制
        CodeProblemSubmitVO vo = judgeRecordDao.selectCodeProblemSubmit(recordId);
        vo.setSubmitCode(EncryptUtils.encryptByBase64(vo.getSubmitCode()));
        //获取测试样例
        List<CodeSampleVO> sampleVOList = eduProblemApi.getSampleList(vo.getProblemId()).getData();
        //遍历样例判题
        sampleVOList.forEach(item -> {
            JudgeCommitVO judgeCommitVO = JudgeCommitVO.builder()
                    .cpuTimeLimit(vo.getTimeLimit())
                    .memoryLimit(vo.getMemoryLimit())
                    .languageId(vo.getLanguageType())
                    .stdin(eduFileApi.getFileContent(item.getInputPath()).getData())
                    .expectedOutput(eduFileApi.getFileContent(item.getOutputPath()).getData())
                    .sourceCode(vo.getSubmitCode())
                    .build();

            JSONObject result = Judge0Http(judgeCommitVO);

            JudgeResultVO resultVO = JudgeResultVO.builder()
                    .runtime(result.getBigDecimal("time"))
                    .resultCode(result.getInt("status_id"))
                    .memory(result.getInt("memory"))
                    .recordId(recordId)
                    .sampleId(item.getId())
                    .build();
            //写入样例运行结果
            judgeRecordSampleDao.insert(resultVO);
        });
        //更新运行结果
        judgeRecordDao.updateRecord(recordId);
        judgeAfter(recordId, vo.getProblemId(), 3);
    }


    @Transactional(rollbackFor = Exception.class)
    public void judgeAfter(Long recordId, Long problemId, int type) {
        JudgeRecordSubmitVO vo = judgeRecordDao.selectResult(recordId);
        //更新题目回答次数/正确次数
        if (type == 1) {
            eduProblemApi.updateChoiceSubmitTimes(problemId, vo.getSubmitStatus() == 3);
        } else if (type == 2) {
            eduProblemApi.updateFillSubmitTimes(problemId, vo.getSubmitStatus() == 3);
        } else if (type == 3) {
            eduProblemApi.updateCodeSubmitTimes(problemId, vo.getSubmitStatus() == 3);
        }

        //结束判题更新用户答题次数/准确次数
        if (vo.getSubmitStatus() == 3) {
             eduTeachApi.updateSubmitCorrectTimes(vo.getUserId(), 1);
        } else {
            eduTeachApi.updateSubmitCorrectTimes(vo.getUserId(), 0);
        }

    }


    @SneakyThrows
    public JSONObject Judge0Http(JudgeCommitVO vo) {
        String result = HttpRequest.post(JudgeFinalValue.SUBMIT_POST_URL)
                .body(vo.toJsonString())
                .header("Content-Type", "application/json")
                .header("X-Edu-Token", JudgeFinalValue.X_EDU_TOKEN)
                .header("X-Edu-Admin", JudgeFinalValue.X_EDU_ADMIN)
                .execute().body();
        return new JSONObject(result);
    }


}
