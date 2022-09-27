package net.edu.module.convert;

import net.edu.module.entity.ProblemResourceEntity;
import net.edu.module.vo.ProblemResourceVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 问题资源表
*
* @author 周建超 
* @since 1.0.0 2022-09-20
*/
@Mapper
public interface ProblemResourceConvert {
    ProblemResourceConvert INSTANCE = Mappers.getMapper(ProblemResourceConvert.class);

    ProblemResourceEntity convert(ProblemResourceVO vo);

    ProblemResourceVO convert(ProblemResourceEntity entity);

    List<ProblemResourceVO> convertList(List<ProblemResourceEntity> list);

}