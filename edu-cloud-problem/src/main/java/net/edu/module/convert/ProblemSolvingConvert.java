package net.edu.module.convert;

import net.edu.module.entity.ProblemResourceEntity;
import net.edu.module.entity.ProblemSolvingEntity;
import net.edu.module.vo.ProblemResourceVO;
import net.edu.module.vo.ProblemSolvingVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 问题资源表
*
* @author sqw
* @since 1.0.0 2022-09-20
*/
@Mapper
public interface ProblemSolvingConvert {
    ProblemSolvingConvert INSTANCE = Mappers.getMapper(ProblemSolvingConvert.class);

    ProblemSolvingEntity convert(ProblemSolvingVO vo);

    ProblemSolvingVO convert(ProblemSolvingEntity entity);

    List<ProblemSolvingVO> convertList(List<ProblemSolvingEntity> list);

}