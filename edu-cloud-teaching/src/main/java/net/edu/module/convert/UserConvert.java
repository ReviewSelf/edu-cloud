package net.edu.module.convert;

import net.edu.framework.security.user.UserDetail;
import net.edu.module.entity.UserEntity;
import net.edu.module.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserVO convert(UserEntity entity);

    UserEntity convert(UserVO vo);

    UserVO convert(UserDetail userDetail);

    UserDetail convertDetail(UserEntity entity);

    List<UserVO> convertList(List<UserEntity> list);

}
