package net.edu.module.dao;

import net.edu.module.vo.TeachStatisticsAddInfoVO;
import net.edu.module.vo.TeachStatisticsInfoVO;
import net.edu.module.vo.ChartVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeachStatisticsDao {

    TeachStatisticsInfoVO selectStatisticsInfo();

    List<ChartVO> selectUserActivity(Integer day);

    List<ChartVO> selectProblemSubmit(Integer day);

    int insertStatisticsInfo();

    TeachStatisticsAddInfoVO selectStatisticsAddInfo();

}
