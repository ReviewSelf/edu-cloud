package net.edu.module.service;

import net.edu.framework.common.utils.Result;

public interface StudentLessonService {


    Result<String> attendLesson(Long lessonId);
}
