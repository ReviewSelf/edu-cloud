package net.edu.module.service;

import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.TeachStatisticsInfoEntity;
import org.springframework.stereotype.Service;


@Service
public interface TeachStatisticsService {
    TeachStatisticsInfoEntity getStatisticsInfo();
    void statisticsHomeInfo();
}
