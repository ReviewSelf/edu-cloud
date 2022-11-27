package net.edu.module.service;

import cn.hutool.json.JSONObject;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.ArchiveGoalScoreEntity;
import net.edu.module.vo.ArchiveGoalScoreInBooKVO;

/**
 * 考试成绩表
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-11-16
 */
public interface ArchiveGoalScoreService extends BaseService<ArchiveGoalScoreEntity> {

    ArchiveGoalScoreInBooKVO getGradeInfo(JSONObject classInfo, String id);
}
