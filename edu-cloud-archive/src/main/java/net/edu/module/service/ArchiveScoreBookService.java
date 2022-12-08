package net.edu.module.service;




import cn.hutool.json.JSONObject;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.query.ArchiveScoreBookQuery;
import net.edu.module.vo.ArchiveScoreBookClassInfoVO;
import net.edu.module.vo.ArchiveScoreBookClassTableVO;
import net.edu.module.vo.ArchiveScoreBookVO;
import net.edu.module.vo.ArchiveScoreInBookVO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 记分册
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-11-22
 */
public interface ArchiveScoreBookService extends BaseService<net.maku.entity.ArchiveScoreBookEntity> {

    PageResult<ArchiveScoreBookVO> page(ArchiveScoreBookQuery query);

    List<ArchiveScoreBookClassTableVO> getClassTable(String id);

    void deleteClassTable(String id,String deleteId);

    List<ArchiveScoreInBookVO> getScoreListInBook(JSONObject classInfo, String id);


    void save(ArchiveScoreBookVO vo);

    void update(ArchiveScoreBookVO vo);

    void delete(List<Long> idList);

    void InsertClassInfo(ArchiveScoreBookClassInfoVO vo);

    void updateClassTable(String id,Object dataForm);

    void addTeachNotes(String dataForm,String booKId);

    void addAnswerNotes(String dataForm,String booKId);

    void createScoreBookWord(Long bookId, HttpServletResponse response) throws IOException;

    void exportExcelScore(HttpServletResponse response) throws IOException;

    ArchiveScoreBookVO selectScoreBookById(Long id);

}
