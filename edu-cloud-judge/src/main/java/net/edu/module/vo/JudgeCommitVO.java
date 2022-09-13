package net.edu.module.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


/**
 * @Author: 马佳浩
 * @Date: 2022/9/7 14:55
 * @Version: 1.0
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JudgeCommitVO {

    //时间限制
    private BigDecimal cpuTimeLimit;
     //空间限制
    private Integer memoryLimit;
    //输入
    private String stdin;

    //语言
    private Integer languageId;
    //输出
    private String expectedOutput;
    //代码
    private String sourceCode;


    public String toJsonString() {
        return "{\"cpu_time_limit\":\""+cpuTimeLimit
                +"\",\"memory_limit\":\""+memoryLimit
                +"\",\"stdin\":\""+stdin
                +"\",\"language_id\":\""+languageId
                +"\",\"expected_output\":\""+expectedOutput
                +"\",\"source_code\":\""+sourceCode
                +"\"}";
    }


}
