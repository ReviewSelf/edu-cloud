package net.edu.mini.service;

import net.edu.mini.vo.MyLessonVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WxMiniService {
    List<MyLessonVo> getLesson(String time, String userId);
}
