package net.edu.module.convert;

import net.edu.framework.security.user.UserDetail;
import net.edu.module.entity.UserEntity;
import net.edu.module.vo.TeacherVO;
import net.edu.module.vo.affairTeacherVO;
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

    affairTeacherVO convert(UserEntity entity);

    UserEntity convert(affairTeacherVO vo);

    affairTeacherVO convert(UserDetail userDetail);

    UserDetail convertDetail(UserEntity entity);

    List<affairTeacherVO> convertList(List<UserEntity> list);
}
