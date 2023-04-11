package net.edu.module.controller;

import cn.hutool.json.JSONObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.module.api.EduTeachApi;
import net.edu.module.dto.MessageDTO;
import net.edu.module.entity.MsgLogEntity;
import net.edu.module.service.SysUserService;
import net.edu.module.service.WeChatMsgService;
import net.edu.module.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
* 新增模块演示
*
* @author 阿沐 babamu@126.com
*/
@RestController
@RequestMapping("wx")
@Tag(name="消息推送")
@AllArgsConstructor
public class WeChatMsgController {


    @Autowired
    private EduTeachApi eduTeachApi;


    @Autowired
    private WeChatMsgService weChatMsgService;

    @Autowired
    private SysUserService sysUserService;




    /**
     * 发送模板消息(定时器自动查询）
     */
//    @RequestMapping(value = "/send", method = RequestMethod.POST)
//    public void sendTemplateMessage() {
//        weChatMsgService.sentTemplate();
//    }

    /**
     * 手动发送
     * @param msgLogEntity
     * @return
     */
//    @RequestMapping(value = "/msg", method = RequestMethod.POST)
//    public Result<String> sendMessage(@RequestBody MsgLogEntity msgLogEntity) {
//        weChatMsgService.sentMessage(msgLogEntity);
//        return Result.ok();
//    }

    @PostMapping("post")
    @Operation(summary = "报名")
    public Result<String> post(@RequestBody EnrollUserVO enrollUserVO){
        Integer userId = enrollUserVO.getId();
        if(userId==null){
            eduTeachApi.insertEnrollUser(enrollUserVO).getData();
        }else{
            eduTeachApi.post(enrollUserVO);
        }
        return Result.ok();
    }


    @GetMapping("info")
    public Result<UserVO> getUserInfo(@RequestParam("openId") String openId) {
        return Result.ok(sysUserService.getUserInfo(openId));
    }

//    <!--插入开课提醒模板消息-->
//    @PostMapping("classOpen")
//    public Result<String> insertClassOpenTemplate(@RequestBody List<WxClassOpenVO> vo){
//        weChatMsgService.insertMsgLogClassOpenTemplate(vo);
//        return Result.ok();
//    }

//    <!--插入作业发布提醒模板消息-->
//    @PostMapping("workPublish")
//    public Result<String> insertWorkPublishTemplate(@RequestBody List<WxWorkPublishVO> vo){
//        weChatMsgService.insertMsgLogWorkPublishTemplate(vo);
//        return Result.ok();
//    }

//    <!--插入上课前提醒模板消息-->
//    @PostMapping("lessonOpen")
//    public Result<String> insertLessonOpenTemplate(@RequestBody List<WxLessonOpenVO> vo){
//        weChatMsgService.insertMsgLogLessonOpenTemplate(vo);
//        return Result.ok();
//    }

//    @PostMapping("signSuccess")
//    public Result<String> insertSignSuccessTemplate(@RequestBody List<WxSignSuccessVO> vo){
//        weChatMsgService.insertMsgLogSignSuccessTemplate(vo);
//        return Result.ok();
//    }

//    <!--作业提醒模板消息-->
//    @PostMapping("workDeadline")
//    public Result<String> insertWorkDeadlineTemplate(@RequestBody List<WxWorkDeadlineVO> vo){
//        weChatMsgService.insertMsgLogWorkDeadlineTemplate(vo);
//        return Result.ok();
//    }

//    <!--课堂评价提醒-->
//    @PostMapping("lessonEvaluation")
//    public Result<String> insertLessonEvaluationTemplate(@RequestBody List<WxLessonEvaluationVO> vo){
//        weChatMsgService.insertMsgLogLessonEvaluationTemplate(vo);
//        return Result.ok();
//    }

    //    <!--考试安排提醒-->
//    @PostMapping("examArrangement")
//    public Result<String> insertExamArrangementTemplate(@RequestBody List<WxExamArrangementVO> vo){
//        weChatMsgService.insertMsgLogExamArrangementTemplate(vo);
//        return Result.ok();
//    }

    /**
     * 消息群发
     *
     * @param obj
     * @return
     */
    @PostMapping(value = "groupMessage", produces = "application/json;charset=utf-8")
    public Result<String> Message(@RequestBody(required = false) JSONObject obj) {
        weChatMsgService.sentBatchMessage(obj);
        return Result.ok();
    }

    private MessageDTO getMessageDTO(String content, List<Long> userId) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setUserId(userId);
        messageDTO.setContent(content);
        return messageDTO;
    }

    /**
     * 课堂开课通知
     *
     * @param vo
     * @return
     */
    @PostMapping("classOpen")
    public Result<String> insertClassOpenTemplate(@RequestBody List<WxClassOpenVO> vo) {

        String content = vo.get(0).toJsonString();
        List<Long> userId = new ArrayList<>();
        for (WxClassOpenVO wxClassOpenVO : vo) {
            userId.add(wxClassOpenVO.getUserId());
        }
        MessageDTO messageDTO = getMessageDTO(content, userId);
        weChatMsgService.sentClassOpenMsg(messageDTO);
        return Result.ok();
    }


    /**
     * 作业布置通知
     *
     * @param vo
     * @return
     */
    @PostMapping("workPublish")
    public Result<String> insertWorkPublishTemplate(@RequestBody List<WxWorkPublishVO> vo) {

        String content = vo.get(0).toJsonString();
        List<Long> userId = new ArrayList<>();
        for (WxWorkPublishVO wxWorkPublishVO : vo) {
            userId.add(wxWorkPublishVO.getUserId());
        }
        MessageDTO messageDTO = getMessageDTO(content, userId);
        weChatMsgService.sentHomeworkMsg(messageDTO);
        return Result.ok();
    }


    //    <!--插入上课前提醒模板消息-->
    @PostMapping("lessonOpen")
    public Result<String> insertLessonOpenTemplate(@RequestBody List<WxLessonOpenVO> vo) {
        String content = vo.get(0).toJsonString();
        List<Long> userId = new ArrayList<>();
        for (WxLessonOpenVO wxLessonOpenVp : vo) {
            userId.add(wxLessonOpenVp.getUserId());
        }
        MessageDTO messageDTO = getMessageDTO(content, userId);
        weChatMsgService.sentLessonOpenMsg(messageDTO);
        return Result.ok();
    }

//    签到成功模板消息
    @PostMapping("signSuccess")
    public Result<String> insertSignSuccessTemplate(@RequestBody List<WxSignSuccessVO> vo){
        String content = vo.get(0).toJsonString();
        List<Long> userId = new ArrayList<>();
        for (WxSignSuccessVO wxSignSuccessVO : vo) {
            userId.add(wxSignSuccessVO.getUserId());
        }
        MessageDTO messageDTO = getMessageDTO(content, userId);
        weChatMsgService.sentSignSuccessMsg(messageDTO);
        return Result.ok();
    }

    //    <!--作业提醒模板消息-->
    @PostMapping("workDeadline")
    public Result<String> insertWorkDeadlineTemplate(@RequestBody List<WxWorkDeadlineVO> vo){
        String content = vo.get(0).toJsonString();
        List<Long> userId = new ArrayList<>();
        for (WxWorkDeadlineVO wxWorkDeadlineVO : vo) {
            userId.add(wxWorkDeadlineVO.getUserId());
        }
        MessageDTO messageDTO = getMessageDTO(content, userId);
        weChatMsgService.sentWorkDeadlineMsg(messageDTO);
        return Result.ok();
    }

    //    <!--课堂评价提醒-->
    @PostMapping("lessonEvaluation")
    public Result<String> insertLessonEvaluationTemplate(@RequestBody List<WxLessonEvaluationVO> vo){
        String content = vo.get(0).toJsonString();
        List<Long> userId = new ArrayList<>();
        for (WxLessonEvaluationVO wxLessonEvaluationVO : vo) {
            userId.add(wxLessonEvaluationVO.getUserId());
        }
        MessageDTO messageDTO = getMessageDTO(content, userId);
        weChatMsgService.sentLessonEvaluationMsg(messageDTO);
        return Result.ok();
    }

    //    <!--考试安排提醒-->
    @PostMapping("examArrangement")
    public Result<String> insertExamArrangementTemplate(@RequestBody List<WxExamArrangementVO> vo){
        String content = vo.get(0).toJsonString();
        List<Long> userId = new ArrayList<>();
        for (WxExamArrangementVO wxExamArrangementVO : vo) {
            userId.add(wxExamArrangementVO.getUserId());
        }
        MessageDTO messageDTO = getMessageDTO(content, userId);
        weChatMsgService.sentExamArrangementMsg(messageDTO);
        return Result.ok();
    }
}



















