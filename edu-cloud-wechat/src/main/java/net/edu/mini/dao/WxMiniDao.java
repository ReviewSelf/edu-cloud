package net.edu.mini.dao;

import net.edu.mini.vo.MyLessonVo;
import net.edu.mini.vo.MyMessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WxMiniDao {

    List<MyLessonVo> selectLesson(String time, String userId);

    MyMessage selectMessage(String userId);
}
