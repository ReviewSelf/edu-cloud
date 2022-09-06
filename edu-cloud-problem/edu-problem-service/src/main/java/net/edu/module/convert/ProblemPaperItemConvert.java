package net.edu.module.convert;

import net.edu.module.entity.ProblemPaperItemEntity;
import net.edu.module.vo.ProblemPaperItemVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 问题卷关联问题表
*
* @author 樊磊 
* @since 1.0.0 2022-09-06
*/
@Mapper
public interface ProblemPaperItemConvert {
    ProblemPaperItemConvert INSTANCE = Mappers.getMapper(ProblemPaperItemConvert.class);

    ProblemPaperItemEntity convert(ProblemPaperItemVO vo);

    ProblemPaperItemVO convert(ProblemPaperItemEntity entity);

    List<ProblemPaperItemVO> convertList(List<ProblemPaperItemEntity> list);

}