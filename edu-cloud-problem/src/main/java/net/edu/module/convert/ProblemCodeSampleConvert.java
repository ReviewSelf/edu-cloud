package net.edu.module.convert;

import net.edu.module.entity.ProblemCodeSampleEntity;
import net.edu.module.vo.ProblemCodeSampleVO;
import net.edu.module.vo.SampleVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 测试样例表
*
* @author sqw 
* @since 1.0.0 2022-09-07
*/
@Mapper
public interface ProblemCodeSampleConvert {
    ProblemCodeSampleConvert INSTANCE = Mappers.getMapper(ProblemCodeSampleConvert.class);

    ProblemCodeSampleEntity convert(ProblemCodeSampleVO vo);

    ProblemCodeSampleEntity convert(SampleVO vo);

    ProblemCodeSampleVO convert(ProblemCodeSampleEntity entity);



    List<ProblemCodeSampleVO> convertList(List<ProblemCodeSampleEntity> list);

}