package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.ArchiveSignEntity;
import net.edu.module.query.ArchiveSignQuery;
import net.edu.module.vo.ArchiveSignVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArchiveSignService extends BaseService<ArchiveSignEntity> {

    void signImportExcel(MultipartFile file,Long bookId);
    PageResult<ArchiveSignVO> page(ArchiveSignQuery query);

    void save(ArchiveSignVO vo);

    void update(ArchiveSignVO vo);

    void delete(List<Long> idList);
}
