package net.edu.module.convert;

import net.edu.framework.security.user.UserDetail;
import net.edu.module.entity.UserEntity;
import net.edu.module.vo.AffairTeacherVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description: TODO
 * @author: sl
 * @date: 2022年10月10日 17:06
 */
@Mapper
public interface AffairTeacherConvert {
    AffairTeacherConvert INSTANCE = Mappers.getMapper(AffairTeacherConvert.class);

    AffairTeacherVO convert(UserEntity entity);

    UserEntity convert(AffairTeacherVO vo);

    AffairTeacherVO convert(UserDetail userDetail);

    UserDetail convertDetail(UserEntity entity);

    List<AffairTeacherVO> convertList(List<UserEntity> list);
}
