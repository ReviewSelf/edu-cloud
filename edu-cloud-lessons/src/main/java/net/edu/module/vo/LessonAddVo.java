package net.edu.module.vo;


import lombok.Data;
import net.edu.module.api.vo.ProblemPaperItemEntity;

import java.util.List;

@Data
public class LessonAddVo {
    private Long lessonId;

    private List<ProblemPaperItemEntity> list;
}
