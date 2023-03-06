package net.edu.module.convert;

import net.edu.module.entity.TeachClassHoursFlowRecordEntity;
import net.edu.module.vo.TeachClassHoursFlowRecordVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 课时流水管理
*
* @author sqw 
* @since  2023-03-06
*/
@Mapper
public interface TeachClassHoursFlowRecordConvert {
    TeachClassHoursFlowRecordConvert INSTANCE = Mappers.getMapper(TeachClassHoursFlowRecordConvert.class);

    TeachClassHoursFlowRecordEntity convert(TeachClassHoursFlowRecordVO vo);

    TeachClassHoursFlowRecordVO convert(TeachClassHoursFlowRecordEntity entity);

    List<TeachClassHoursFlowRecordVO> convertList(List<TeachClassHoursFlowRecordEntity> list);

}