package net.edu.module.dao;

import net.edu.module.vo.TeachPlanItemPaperVO;
import net.edu.module.vo.TeachPlanItemResourceVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeachPlanItemResourceDao {

    List<TeachPlanItemResourceVO> selectItemResource(@Param("id") Long id);

    int deletedItemResource(@Param("id") Long id);

    int insertItemResource(@Param("vo") TeachPlanItemResourceVO vo);
}
