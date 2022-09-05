package net.edu.module.convert;

import net.edu.module.entity.CodeProblemEntity;
import net.edu.module.vo.CodeProblemVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 代码题库表
*
* @author 马佳浩 
* @since 1.0.0 2022-09-05
*/
@Mapper
public interface CodeProblemConvert {
    CodeProblemConvert INSTANCE = Mappers.getMapper(CodeProblemConvert.class);

    CodeProblemEntity convert(CodeProblemVO vo);

    CodeProblemVO convert(CodeProblemEntity entity);

    List<CodeProblemVO> convertList(List<CodeProblemEntity> list);

}