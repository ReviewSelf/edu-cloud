package net.edu.module.entity;

import lombok.Data;
import net.edu.module.vo.ChartVO;

import java.util.List;

@Data
public class TeachStatisticsInfoEntity {
    private Long id; //自增id
    private String statisticsDate; //统计日期，唯一索引
    private Integer questionNum; //题库日增量
    private Integer userNum; //用户日增量
    private Integer userActivity; //用户每日活跃量
    private Integer problemSubmitNum; //题目提交日增量
    private List<ChartVO> userActivityTotal;//近七天用户活跃量列表
    private List<ChartVO> problemSubmitTotal;//近30天题目提交量列表
}
