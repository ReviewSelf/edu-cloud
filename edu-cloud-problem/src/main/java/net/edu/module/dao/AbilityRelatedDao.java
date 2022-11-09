package net.edu.module.dao;

import net.edu.module.vo.AbilityPointVO;
import net.edu.module.vo.AbilityRelatedVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AbilityRelatedDao {
    int deleteRelated(Long id);

    int insertRelated(@Param("vo") AbilityRelatedVO vo);

    List<AbilityRelatedVO> selectRelatedList(Long id);
    AbilityRelatedVO selectRelated(Long id);
}
