package net.edu.module.vo;

import lombok.Data;

/**
 * @Author: 马佳浩
 * @Date: 2022/11/16 9:18
 * @Version: 1.0
 * @Description:
 */
@Data
public class TeachStatisticsAddInfoVO {
    private String statisticsDate;
    //题库增量
    private Integer questionNum;
    //用户增量
    private Integer userNum;
    //用户活跃增量
    private Integer userActivity;
    //题目提交增量
    private Integer problemSubmitNum;
}
