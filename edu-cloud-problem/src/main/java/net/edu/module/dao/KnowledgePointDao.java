package net.edu.module.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.KnowledgePointEntity;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/6 13:15
 * @Version: 1.0
 * @Description:
 */
@Mapper
public interface KnowledgePointDao  extends BaseDao<KnowledgePointEntity> {
    List<KnowledgePointEntity> getKpList();

    KnowledgePointEntity selectBrotherEntity(Long pid,String code);
}
