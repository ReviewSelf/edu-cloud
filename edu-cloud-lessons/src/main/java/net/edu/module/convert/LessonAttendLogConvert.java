package net.edu.module.convert;


import net.edu.module.entity.LessonAttendLogEntity;
import net.edu.module.vo.LessonAttendLogVO;
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
public interface LessonAttendLogConvert {
    LessonAttendLogConvert INSTANCE = Mappers.getMapper(LessonAttendLogConvert.class);

    LessonAttendLogEntity convert(LessonAttendLogVO vo);

    LessonAttendLogVO convert(LessonAttendLogEntity entity);

    List<LessonAttendLogVO> convertList(List<LessonAttendLogEntity> list);

}