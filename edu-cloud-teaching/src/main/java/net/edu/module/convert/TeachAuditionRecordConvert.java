package net.edu.module.convert;

import net.edu.module.entity.TeachAuditionRecordEntity;
import net.edu.module.vo.TeachAuditionRecordVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 教学试听安排
*
* @author sqw 
* @since 1.0.0 2023-02-13
*/
@Mapper
public interface TeachAuditionRecordConvert {
    TeachAuditionRecordConvert INSTANCE = Mappers.getMapper(TeachAuditionRecordConvert.class);

    TeachAuditionRecordEntity convert(TeachAuditionRecordVO vo);

    TeachAuditionRecordVO convert(TeachAuditionRecordEntity entity);

    List<TeachAuditionRecordVO> convertList(List<TeachAuditionRecordEntity> list);

}