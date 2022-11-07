package net.edu.module.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.KnowledgePointEntity;
import net.edu.module.query.KpProblemQuery;
import net.edu.module.vo.CodeProblemVO;
import net.edu.module.vo.KpProblemVO;
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

    IPage<KpProblemVO> selectKpProblem(Page<KpProblemVO> page, KpProblemQuery query);
}
