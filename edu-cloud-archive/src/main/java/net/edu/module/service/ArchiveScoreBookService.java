package net.edu.module.service;




import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.query.ArchiveScoreBookQuery;
import net.edu.module.vo.ArchiveScoreBookClassInfoVO;
import net.edu.module.vo.ArchiveScoreBookClassTableVO;
import net.edu.module.vo.ArchiveScoreBookVO;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * 记分册
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-11-22
 */
public interface ArchiveScoreBookService extends BaseService<net.maku.entity.ArchiveScoreBookEntity> {

    PageResult<ArchiveScoreBookVO> page(ArchiveScoreBookQuery query);

    List<ArchiveScoreBookClassTableVO> getClassTable(Long id);




    void save(ArchiveScoreBookVO vo);

    void update(ArchiveScoreBookVO vo);

    void delete(List<Long> idList);

    void InsertClassInfo(ArchiveScoreBookClassInfoVO vo);



}
