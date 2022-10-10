package net.edu.module.service.impl;

import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.DateUtils;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.dao.TeachStatisticsDao;
import net.edu.module.dao.UserDao;
import net.edu.module.entity.TeachStatisticsInfoEntity;
import net.edu.module.entity.UserEntity;
import net.edu.module.service.TeachStatisticsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


@Service
@AllArgsConstructor
public  class TeachStatisticsServiceImpl  implements TeachStatisticsService {

    private final TeachStatisticsDao statisticsDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TeachStatisticsInfoEntity getStatisticsInfo() {
        //去除最近一次统计的题库总量、用户总量、问题提交总量以及前一天用户总量
        TeachStatisticsInfoEntity statisticsInfo = statisticsDao.selectStatisticsInfo();
        //统计最近30天用户活跃量
        statisticsInfo.setUserActivityTotal(statisticsDao.selectUserActivity(30));
        //统计最近7天的每天题目提交趋势
        statisticsInfo.setProblemSubmitTotal(statisticsDao.selectProblemSubmit(7));
        return statisticsInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void statisticsHomeInfo(){
        //统计题库日增量/用户日增量/统计问题提交日增量/用户每天活跃量并将统计结果存入统计表中
        statisticsDao.insertStatisticsInfo();
    }

}
