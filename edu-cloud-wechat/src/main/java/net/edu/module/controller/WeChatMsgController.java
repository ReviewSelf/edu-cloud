package net.edu.module.controller;

import cn.hutool.json.JSONObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.module.api.EduTeachApi;
import net.edu.module.entity.*;
import net.edu.module.service.SysUserService;
import net.edu.module.service.WeChatMsgService;
import net.edu.module.service.WeChatService;
import net.edu.module.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
    private WeChatService weChatService;
    @Autowired
    private SysUserService sysUserService;




    /**
     * 发送模板消息(定时器自动查询）
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public void sendTemplateMessage() {
        weChatMsgService.sentTemplate();
    }

    /**
     * 手动发送
     * @param msgLogEntity
     * @return
     */
    @RequestMapping(value = "/msg", method = RequestMethod.POST)
    public Result<String> sendMessage(@RequestBody MsgLogEntity msgLogEntity) {
        weChatMsgService.sentMessage(msgLogEntity);
        return Result.ok();
    }

    @PostMapping("post")
    @Operation(summary = "报名")
    public Result<String> post(@RequestBody EnrollUserVO enrollUserVO){
        Integer userId = enrollUserVO.getId();
        eduTeachApi.post(enrollUserVO);
        System.out.println(enrollUserVO);
        //如果用户填写的是报名意向
        if(enrollUserVO.getPurpose()=="" || enrollUserVO.getPurpose()==null){
            Integer classId = enrollUserVO.getClassId();
            System.out.println(userId);
            eduTeachApi.insertClassUser(classId,userId);
        }
        return Result.ok();
    }



    @GetMapping("info")
    public Result<UserVO> getUserInfo(@RequestParam("openId") String openId){
        System.out.println("info"+openId);
        return Result.ok(sysUserService.getUserInfo(openId));
    }

    @PostMapping("classOpen")
    public Result<String> insertClassOpenTemplate(@RequestBody List<ClassOpenVO> vo){
        weChatMsgService.insertMsgLogClassOpenTemplate(vo);
        return Result.ok();
    }

    @PostMapping("workPublish")
    public Result<String> insertWorkPublishTemplate(@RequestBody List<WorkPublishVO> vo){
        weChatMsgService.insertMsgLogWorkPublishTemplate(vo);
        return Result.ok();
    }

    @PostMapping("lessonOpen")
    public Result<String> insertLessonOpenTemplate(@RequestBody List<LessonOpenVO> vo){
        weChatMsgService.insertMsgLogLessonOpenTemplate(vo);
        return Result.ok();
    }

    @PostMapping("signSuccess")
    public Result<String> insertSignSuccessTemplate(@RequestBody List<SignSuccessVO> vo){
        weChatMsgService.insertMsgLogSignSuccessTemplate(vo);
        return Result.ok();
    }

    @PostMapping("workDeadline")
    public Result<String> insertWorkDeadlineTemplate(@RequestBody List<WorkDeadlineVO> vo){
        weChatMsgService.insertMsgLogWorkDeadlineTemplate(vo);
        return Result.ok();
    }

    /**
     * 消息群发
     * @param obj
     * @return
     */
    @PostMapping(value="groupMessage",produces ="application/json;charset=utf-8")
    public Result<String> Message(@RequestBody(required=false)JSONObject obj) {
        weChatMsgService.sentBatchMessage(obj);
        return Result.ok();
    }


    /**
     * 账号绑定
     * @param username
     * @param password
     * @return
     */
    @PutMapping("account")
    public Result<Integer> updateOpenIdByUsername(@RequestParam("username") String username,
                                                  @RequestParam("password") String password,
                                                  @RequestParam("openId") String openId){
        return Result.ok(sysUserService.updateOpenIdByUsername(username,password,openId));
    }
}



















