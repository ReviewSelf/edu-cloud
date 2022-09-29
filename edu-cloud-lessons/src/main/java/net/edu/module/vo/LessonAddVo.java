package net.edu.module.vo;


import lombok.Data;


import java.util.List;

@Data
public class LessonAddVo {
    private Long lessonId;

    private List<ProblemPaperItemEntity> list;
}
