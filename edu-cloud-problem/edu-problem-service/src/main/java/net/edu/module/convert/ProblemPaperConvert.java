package net.edu.module.convert;


import net.edu.module.entity.ProblemPaperEntity;
import net.edu.module.vo.ProblemPaperVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 问题卷表
*
* @author 樊磊 
* @since 1.0.0 2022-09-05
*/
@Mapper
public interface ProblemPaperConvert {
    ProblemPaperConvert INSTANCE = Mappers.getMapper(ProblemPaperConvert.class);

    ProblemPaperEntity convert(ProblemPaperVO vo);

    ProblemPaperVO convert(ProblemPaperEntity entity);

    List<ProblemPaperVO> convertList(List<ProblemPaperEntity> list);

}