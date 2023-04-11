package net.edu.module.dto;

import lombok.Data;

import java.util.List;

/**
 * @author weng
 *
 * @date 2023/4/10 - 19:47
 **/
@Data
public class MessageDTO {

    //内容
    private String content;

    //需要接受到消息的用户id
    private List<Long> userId;


}