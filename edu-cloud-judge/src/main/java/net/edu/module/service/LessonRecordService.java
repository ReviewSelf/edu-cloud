package net.edu.module.service;

import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;
import net.edu.module.dao.LessonRecordDao;
import net.edu.module.vo.lesson.LessonProblemRankVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: 马佳浩
 * @Date: 2022/10/14 20:25
 * @Version: 1.0
 * @Description:
 */
@Service
@AllArgsConstructor
public class LessonRecordService {

    private final LessonRecordDao lessonRecordDao;


    public List<LessonProblemRankVO> getLessonProblemRank(Long lessonId, Integer type) {
        //按照正确数降序序
        List<LessonProblemRankVO> list = lessonRecordDao.selectLessonProblemRank(lessonId, type);
        // 10 10 9 8 8 7 对应名次 1 1 3 4 4 6
        if (CollUtil.isNotEmpty(list)) {
            list.get(0).setRankNum(1);
            if (list.size() > 1) {
                for (int i = 1; i < list.size(); i++) {
                    if (list.get(i).getCorrectNum().equals(list.get(i - 1).getCorrectNum())) {
                        list.get(i).setRankNum(list.get(i - 1).getRankNum());
                    }
                    if (list.get(i).getCorrectNum() < list.get(i - 1).getCorrectNum()) {
                        list.get(i).setRankNum(i + 1);
                    }
                }
            }
        }
        return list;
    }


}
