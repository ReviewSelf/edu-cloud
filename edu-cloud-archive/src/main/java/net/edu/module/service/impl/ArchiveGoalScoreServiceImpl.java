package net.edu.module.service.impl;

import lombok.AllArgsConstructor;
import net.edu.framework.common.excel.ExcelSyncDataListener;
import net.edu.framework.common.utils.ExcelUtils;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.dao.*;
import net.edu.module.entity.ArchiveGoalScoreEntity;
import net.edu.module.entity.ArchiveTestScoreEntity;
import net.edu.module.entity.ArchiveWeightAssessTestEntity;
import net.edu.module.service.ArchiveGoalScoreService;
import net.edu.module.service.ArchiveTestScoreService;
import net.edu.module.vo.ArchiveTestScoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 考试成绩表
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-11-16
 */
@Service
@AllArgsConstructor
public class ArchiveGoalScoreServiceImpl extends BaseServiceImpl<ArchiveGoalScoreDao, ArchiveGoalScoreEntity> implements ArchiveGoalScoreService {



}
