package net.edu.module.vo;


import lombok.Data;


/**
 * @Author: 马佳浩
 * @Date: 2022/9/7 14:55
 * @Version: 1.0
 * @Description:
 */
@Data
public class JudgeCommitVO {
    Float cpu_time_limit;
    Integer memory_limit;
    String stdin;
    String language_id;
    String expected_output;
    String source_code;


    public String toJsonString() {
        return "{\"cpu_time_limit\":\""+cpu_time_limit
                +"\",\"memory_limit\":\""+memory_limit
                +"\",\"stdin\":\""+stdin
                +"\",\"language_id\":\""+language_id
                +"\",\"expected_output\":\""+expected_output
                +"\",\"source_code\":\""+source_code
                +"\"}";
    }


}
