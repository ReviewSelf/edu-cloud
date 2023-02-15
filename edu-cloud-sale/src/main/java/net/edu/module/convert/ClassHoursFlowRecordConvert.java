package net.edu.module.convert;

import net.edu.module.entity.ClassHoursFlowRecordEntity;
import net.edu.module.vo.ClassHoursFlowRecordVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 课时流水表
*
* @author sqw 
* @since 1.0.0 2023-02-15
*/
@Mapper
public interface ClassHoursFlowRecordConvert {
    ClassHoursFlowRecordConvert INSTANCE = Mappers.getMapper(ClassHoursFlowRecordConvert.class);

    ClassHoursFlowRecordEntity convert(ClassHoursFlowRecordVO vo);

    ClassHoursFlowRecordVO convert(ClassHoursFlowRecordEntity entity);

    List<ClassHoursFlowRecordVO> convertList(List<ClassHoursFlowRecordEntity> list);

}