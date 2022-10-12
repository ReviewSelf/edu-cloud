package net.edu.module.convert;


import net.edu.module.entity.ExamAttendLogEntity;
import net.edu.module.vo.ExamAttendLogVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
* 课堂签到表
*
* @author 马佳浩 
* @since 1.0.0 2022-09-15
*/
@Mapper
public interface ExamAttendLogConvert {
    ExamAttendLogConvert INSTANCE = Mappers.getMapper(ExamAttendLogConvert.class);

    ExamAttendLogEntity convert(ExamAttendLogVO vo);

    ExamAttendLogVO convert(ExamAttendLogEntity entity);

    List<ExamAttendLogVO> convertList(List<ExamAttendLogEntity> list);

}