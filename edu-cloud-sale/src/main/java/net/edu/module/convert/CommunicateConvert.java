package net.edu.module.convert;

import net.edu.framework.security.user.UserDetail;
import net.edu.module.entity.CommunicateEntity;
import net.edu.module.entity.UserEntity;
import net.edu.module.vo.CommunicateVO;
import net.edu.module.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface CommunicateConvert {
    CommunicateConvert INSTANCE = Mappers.getMapper(CommunicateConvert.class);

    CommunicateVO convert(CommunicateEntity entity);

    CommunicateEntity convert(CommunicateVO vo);


    List<CommunicateVO> convertList(List<CommunicateEntity> list);

}
