package net.edu.module.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ProblemPaperEntity;
import net.edu.module.entity.ProblemSolvingEntity;
import net.edu.module.query.ProblemSolvingQuery;
import net.edu.module.vo.ProblemSolvingVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProblemSolvingDao extends BaseDao<ProblemSolvingEntity> {
    IPage<ProblemSolvingVO> selectSolvingList(Page<ProblemSolvingVO> page, @Param("query") ProblemSolvingQuery query);

    ProblemSolvingVO selectSolving(Long id);

    int deleteProblemSolving(Long id);


}
