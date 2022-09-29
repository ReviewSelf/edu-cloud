package net.edu.module.convert;

import net.edu.module.vo.SampleVO;
import net.edu.module.entity.CodeSampleEntity;
import net.edu.module.vo.CodeSampleVO;
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
public interface CodeSampleConvert {
    CodeSampleConvert INSTANCE = Mappers.getMapper(CodeSampleConvert.class);

    CodeSampleEntity convert(CodeSampleVO vo);

    CodeSampleEntity convert(SampleVO vo);

    CodeSampleVO convert(CodeSampleEntity entity);



    List<CodeSampleVO> convertList(List<CodeSampleEntity> list);

}