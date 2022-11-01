package net.edu.module.service;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.vo.ArchiveTargetVO;
import net.edu.module.query.ArchiveTargetQuery;
import net.edu.module.entity.ArchiveTargetEntity;

import java.util.List;

/**
 * target
 *
 * @author qxd babamu@126.com
 * @since 1.0.0 2022-10-24
 */
public interface ArchiveTargetService extends BaseService<ArchiveTargetEntity> {

    PageResult<ArchiveTargetVO> page(ArchiveTargetQuery query);

    List<ArchiveTargetVO> getName(String grade);

    ArchiveTargetVO selectArchiveTargetById(Long id);

    void save(ArchiveTargetVO vo);

    void update(ArchiveTargetVO vo);

    void delete(List<Long> idList);
}
