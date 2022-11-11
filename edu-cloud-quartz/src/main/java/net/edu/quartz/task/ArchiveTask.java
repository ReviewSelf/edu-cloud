package net.edu.quartz.task;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.edu.module.api.EduArchiveApi;
import net.edu.module.api.EduLessonApi;
import org.springframework.stereotype.Component;

/**
 * @Author: 沈凯文
 * @Date: 2022/11/11 14:32
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@Component
@AllArgsConstructor
public class ArchiveTask {
    private final EduArchiveApi eduArchiveApi;

    public void insertExam(String str){
        eduArchiveApi.insertExam();
        log.debug("考试数据迁移完成了");
    }
}
