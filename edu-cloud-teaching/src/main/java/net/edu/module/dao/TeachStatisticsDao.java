package net.edu.module.dao;

import net.edu.module.entity.TeachStatisticsInfoEntity;
import net.edu.module.vo.ChartVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeachStatisticsDao {

    TeachStatisticsInfoEntity selectStatisticsInfo();

    List<ChartVO> selectUserActivity(Integer day);

    List<ChartVO> selectProblemSubmit(Integer day);

    int insertStatisticsInfo();
}
