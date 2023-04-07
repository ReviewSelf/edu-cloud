package net.edu.module.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.edu.framework.common.cache.RedisKeys;
import net.edu.framework.common.exception.ServerException;
import net.edu.framework.common.mq.BindingName;
import net.edu.framework.common.mq.ExchangeName;
import net.edu.framework.common.utils.EncryptUtils;
import net.edu.framework.common.utils.RedisUtils;
import net.edu.module.api.EduFileApi;
import net.edu.module.api.EduProblemApi;
import net.edu.module.api.EduTeachApi;
import net.edu.module.dao.JudgeRecordDao;
import net.edu.module.dao.JudgeRecordSampleDao;
import net.edu.module.vo.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
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
@Slf4j
public class JudgeService {

    private final int choiceType = 1;
    private final int fillType = 2;
    private final int codeType = 3;

    private final RabbitTemplate rabbitTemplate;

    private final EduTeachApi eduTeachApi;

    private final RedisUtils redisUtils;


    private final JudgeRecordDao judgeRecordDao;

    private final EduFileApi eduFileApi;

    private final EduProblemApi eduProblemApi;

    private final JudgeRecordSampleDao judgeRecordSampleDao;


    @SneakyThrows
    public void judgeBefore(JudgeRecordSubmitVO vo) {

        //提交频率限制
        Long second = (Long) redisUtils.getExpire(RedisKeys.getJudgeRecordFlag(vo.getUserId(), vo.getProblemId(), vo.getProblemType(), vo.getSource(), vo.getSourceId()));
        if (second > 0L) {
            throw new ServerException("提交冷却中，请" + second + "秒后重新提交");
        }


        //插入记录
        if (vo.getProblemType() == choiceType) {
            //选择题直接判题
            judgeChoice(vo);
        } else if (vo.getProblemType() == fillType) {
            //填空题只插入
            judgeRecordDao.insertSubmitRecord(vo);
        } else if (vo.getProblemType() == codeType) {
            //代码题先插入后推送至mq
            judgeRecordDao.insertSubmitRecord(vo);
            if (!StrUtil.isEmpty(vo.getSubmitContent())) {
                //提交到mq
                rabbitTemplate.convertAndSend(ExchangeName.DEFAULT_EXCHANGE, BindingName.JUDGE_BINDING, String.valueOf(vo.getId()));
            }
        }

        redisUtils.set(RedisKeys.getJudgeRecordFlag(vo.getUserId(), vo.getProblemId(), vo.getProblemType(), vo.getSource(), vo.getSourceId()), System.currentTimeMillis(), RedisUtils.SECOND_TWENTY_EXPIRE);
    }

    @Transactional(rollbackFor = Exception.class)
    public void judgeChoice(JudgeRecordSubmitVO vo) {
        List<String> arr = Arrays.asList(vo.getSubmitContent().split(";;;"));
        List<String> answer = eduProblemApi.getChoiceOptions(vo.getProblemId(), 1).getData();
        if (arr.size() != answer.size()) {
            vo.setSubmitStatus(JudgeStatusCode.WA);
        } else {
            vo.setSubmitStatus(JudgeStatusCode.AC);
            for (String item : arr) {
                if (!answer.contains(item)) {
                    vo.setSubmitStatus(JudgeStatusCode.WA);
                    break;
                }
            }
        }
        judgeRecordDao.insertSubmitRecord(vo);

        judgeAfter(vo.getId(), vo.getProblemId(), choiceType);
    }

    @Transactional(rollbackFor = Exception.class)
    public void judgeCode(Long recordId) throws Exception {
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
            log.info("提交记录号：{},结果{}", recordId, result);
            JudgeSampleResultVO resultVO = JudgeSampleResultVO.builder()
                    .runtime(result.getBigDecimal("time"))
                    .resultCode(result.getInt("status_id"))
                    .memory(result.getInt("memory"))
                    .recordId(recordId)
                    .sampleId(item.getId())
                    .build();
            if (resultVO.getResultCode() != null) {
                //写入样例运行结果
                judgeRecordSampleDao.insert(resultVO);
            } else {
                //判题机报错，抛异常结束判题
                throw new RuntimeException("判题错误：" + result.toString());
            }
        });
        //更新运行结果
        JudgeSampleResultVO resultVO = judgeRecordDao.selectUpdateRecord(recordId);

        if (resultVO != null) {
            if (resultVO.getResultCode() >= JudgeStatusCode.WA) {
                resultVO.setResultCode(JudgeStatusCode.WA);
            }
            judgeRecordDao.updateRecord(resultVO);
        }

        judgeAfter(recordId, vo.getProblemId(), codeType);
    }


    @Async
    public void judgeAfter(Long recordId, Long problemId, int type) {
        JudgeRecordSubmitVO vo = judgeRecordDao.selectResult(recordId);
        //更新题目回答次数/正确次数
        if (type == choiceType) {
            eduProblemApi.updateChoiceSubmitTimes(problemId, vo.getSubmitStatus() == JudgeStatusCode.AC);
        } else if (type == fillType) {
            eduProblemApi.updateFillSubmitTimes(problemId, vo.getSubmitStatus() == JudgeStatusCode.AC);
        } else if (type == codeType) {
            eduProblemApi.updateCodeSubmitTimes(problemId, vo.getSubmitStatus() == JudgeStatusCode.AC);
        }

        //结束判题更新用户答题次数/准确次数
        if (vo.getSubmitStatus() == JudgeStatusCode.AC) {
            eduTeachApi.updateSubmitCorrectTimes(vo.getUserId(), 1);
            //记录答题量
            if (judgeRecordDao.selectUserRecord(problemId, type, vo.getUserId()) == 1) {
                judgeRecordDao.statisticsUserRecord(problemId, type, vo.getUserId());
            }

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



    public List<String> getFilePath(Long problemId,Integer problemType,Integer source,Long sourceId){
        return judgeRecordDao.selectFilePath(problemId, problemType, source, sourceId);
    }



}
