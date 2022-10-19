package net.edu.module.dao;

import net.edu.module.vo.lesson.LessonJudgeRecordVo;
import net.edu.module.vo.lesson.LessonProblemRankVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface LessonRecordDao {
    List<LessonProblemRankVO> selectLessonProblemRank(Long lessonId, Integer type);

    List<LessonProblemRankVO> selectUserLessonRecord(List<Long> lessonList,Long userId, Integer type);

    List<LessonJudgeRecordVo> selectLessonRecord(Long lessonId, Integer type);
}
