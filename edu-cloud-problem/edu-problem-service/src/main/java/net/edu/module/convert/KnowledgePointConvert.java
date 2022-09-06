package net.edu.module.convert;

import net.edu.module.entity.KnowledgePointEntity;
import net.edu.module.vo.KnowledgePointVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/6 13:20
 * @Version: 1.0
 * @Description:
 */
@Mapper
public interface KnowledgePointConvert {

    KnowledgePointConvert INSTANCE = Mappers.getMapper(KnowledgePointConvert.class);

    KnowledgePointEntity convert(KnowledgePointVO vo);

    KnowledgePointVO convert(KnowledgePointEntity entity);

    List<KnowledgePointVO> convertList(List<KnowledgePointEntity> list);
}
