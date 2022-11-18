package net.edu.module.service;

import net.edu.module.vo.TeachStatisticsAddInfoVO;
import net.edu.module.vo.TeachStatisticsInfoVO;
import org.springframework.stereotype.Service;


@Service
public interface TeachStatisticsService {
    TeachStatisticsInfoVO getStatisticsInfo();
    void statisticsHomeInfo();
    TeachStatisticsAddInfoVO getStatisticsAddInfo();
}
