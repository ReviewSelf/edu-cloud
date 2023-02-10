package net.edu.module.convert;

import net.edu.module.entity.TeachClassRecordEntity;
import net.edu.module.vo.TeachClassRecordVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 学生班级记录
*
* @author sqw 
* @since 1.0.0 2023-02-08
*/
@Mapper
public interface TeachClassRecordConvert {
    TeachClassRecordConvert INSTANCE = Mappers.getMapper(TeachClassRecordConvert.class);

    TeachClassRecordEntity convert(TeachClassRecordVO vo);

    TeachClassRecordVO convert(TeachClassRecordEntity entity);

    List<TeachClassRecordVO> convertList(List<TeachClassRecordEntity> list);

}