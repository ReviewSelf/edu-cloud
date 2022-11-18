package net.edu.module.vo;

import lombok.Data;
import net.edu.module.vo.ChartVO;

import java.io.Serializable;
import java.util.List;

@Data
public class TeachStatisticsInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    //统计日期，唯一索引
    private String statisticsDate;
    //题库总量
    private Integer questionNum;
    //用户总量
    private Integer userNum;
    //用户活跃总量
    private Integer userActivity;
    //题目提交总量
    private Integer problemSubmitNum;
    //近七天用户活跃量列表
    private List<ChartVO> userActivityTotal;
    //近30天题目提交量列表
    private List<ChartVO> problemSubmitTotal;
    private TeachStatisticsAddInfoVO teachStatisticsAddInfoVO;
}
