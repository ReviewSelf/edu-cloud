package net.edu.module.dao;

import net.edu.module.vo.TeachPlanItemPaperVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeachPlanItemPaperDao {

    List<TeachPlanItemPaperVO> selectItemPaper(@Param("id") Long id);

    int deleteItemPaper(@Param("id") Long id);

    int insertItemPaper(@Param("list") List<TeachPlanItemPaperVO> list,@Param("itemId") Long itemId);
}
