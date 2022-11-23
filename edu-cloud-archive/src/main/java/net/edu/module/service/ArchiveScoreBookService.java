package net.edu.module.service;




import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.query.ArchiveScoreBookQuery;
import net.edu.module.vo.ArchiveScoreBookVO;

import java.util.List;

/**
 * 记分册
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-11-22
 */
public interface ArchiveScoreBookService extends BaseService<net.maku.entity.ArchiveScoreBookEntity> {

    PageResult<ArchiveScoreBookVO> page(ArchiveScoreBookQuery query);

    void save(ArchiveScoreBookVO vo);

    void update(ArchiveScoreBookVO vo);

    void delete(List<Long> idList);
}
