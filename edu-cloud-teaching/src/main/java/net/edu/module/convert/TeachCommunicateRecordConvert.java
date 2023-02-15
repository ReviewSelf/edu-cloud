package net.edu.module.convert;

import net.edu.module.entity.TeachCommunicateRecordEntity;
import net.edu.module.vo.TeachCommunicateRecordVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 沟通记录表
*
* @author sqw 
* @since 1.0.0 2023-02-05
*/
@Mapper
public interface TeachCommunicateRecordConvert {
    TeachCommunicateRecordConvert INSTANCE = Mappers.getMapper(TeachCommunicateRecordConvert.class);

    TeachCommunicateRecordEntity convert(TeachCommunicateRecordVO vo);

    TeachCommunicateRecordVO convert(TeachCommunicateRecordEntity entity);

    List<TeachCommunicateRecordVO> convertList(List<TeachCommunicateRecordEntity> list);

}