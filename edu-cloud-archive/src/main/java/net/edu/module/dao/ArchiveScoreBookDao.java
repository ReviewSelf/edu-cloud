package net.edu.module.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.vo.ArchiveScoreBookClassInfoVO;
import net.edu.module.vo.ArchiveScoreBookVO;
import net.maku.entity.ArchiveScoreBookEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* 记分册
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-11-22
*/
@Mapper
public interface ArchiveScoreBookDao extends BaseDao<ArchiveScoreBookEntity> {
   void  InsertClassInfo(ArchiveScoreBookClassInfoVO vo);

   List<ArchiveScoreBookVO> selectList(String id);

   void updateByDeleteId(String id, String classSchedule);

   void updateTeachNotes(String dataForm,String bookId);

   void updateAnswerNotes(String dataForm,String bookId);




}
