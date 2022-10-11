package net.edu.module.convert;

import net.edu.module.entity.WxMsgLogEntity;
import net.edu.module.vo.WxMsgLogVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 消息推送
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-11
*/
@Mapper
public interface WxMsgLogConvert {
    WxMsgLogConvert INSTANCE = Mappers.getMapper(WxMsgLogConvert.class);

    WxMsgLogEntity convert(WxMsgLogVO vo);

    WxMsgLogVO convert(WxMsgLogEntity entity);

    List<WxMsgLogVO> convertList(List<WxMsgLogEntity> list);

}