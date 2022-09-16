package net.edu.module.convert;

import net.edu.framework.security.user.UserDetail;
import net.edu.module.entity.UserEntity;
import net.edu.module.vo.TeacherVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface TeacherConvert {
    TeacherConvert INSTANCE = Mappers.getMapper(TeacherConvert.class);

    TeacherVO convert(UserEntity entity);

    UserEntity convert(TeacherVO vo);

    TeacherVO convert(UserDetail userDetail);

    UserDetail convertDetail(UserEntity entity);

    List<TeacherVO> convertList(List<UserEntity> list);

}
