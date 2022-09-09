package net.edu.module.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ProblemPaperEntity;
import net.edu.module.query.ProblemPaperQuery;
import net.edu.module.vo.ProblemPaperVO;
import org.apache.ibatis.annotations.Mapper;

/**
* 问题卷表
*
* @author 樊磊 
* @since 1.0.0 2022-09-05
*/
@Mapper
public interface ProblemPaperDao extends BaseDao<ProblemPaperEntity> {

    IPage<ProblemPaperVO> page(Page<ProblemPaperVO> page, ProblemPaperQuery query);

    int updateStatus(Long paperId);
}