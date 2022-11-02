package net.edu.module.dao;

import net.edu.module.vo.AbilityPointVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AbilityPointDao {
    int deletePoint(Long id);

    int updatePoint(@Param("vo") AbilityPointVO vo);

    int insertPoint(@Param("list") List<String> list,Long id);

    List<AbilityPointVO> selectList(Long id);

    AbilityPointVO selectPointInfo(Long id);
}
