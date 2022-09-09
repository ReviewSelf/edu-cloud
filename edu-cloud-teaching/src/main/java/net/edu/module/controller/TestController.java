package net.edu.module.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.mq.BindingName;
import net.edu.framework.common.mq.ExchangeName;
import net.edu.framework.common.utils.Result;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.Binding;

/**
* 新增模块演示
*
* @author 阿沐 babamu@126.com
*/
@RestController
@Tag(name="新增模块演示")
@AllArgsConstructor
public class TestController {
    @Autowired
    private RabbitTemplate rabbitTemplate;





    @GetMapping("test")
    public Result<String> test(){
        rabbitTemplate.convertAndSend(ExchangeName.DEFAULT_EXCHANGE, BindingName.JUDGE_BINDING,"hello");
        return Result.ok("ok");
    }


}