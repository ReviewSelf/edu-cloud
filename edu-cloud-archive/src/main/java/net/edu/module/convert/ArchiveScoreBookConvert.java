package net.edu.module.convert;

import net.edu.module.vo.ArchiveScoreBookVO;
import net.maku.entity.ArchiveScoreBookEntity;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 记分册
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-11-22
*/
@Mapper
public interface ArchiveScoreBookConvert {
    ArchiveScoreBookConvert INSTANCE = Mappers.getMapper(ArchiveScoreBookConvert.class);

    ArchiveScoreBookEntity convert(ArchiveScoreBookVO vo);

    ArchiveScoreBookVO convert(ArchiveScoreBookEntity entity);

    List<ArchiveScoreBookVO> convertList(List<ArchiveScoreBookEntity> list);

}
