package net.edu.module.vo;

import lombok.Data;

import java.util.List;

/**
 * @author weng
 * @date 2022/10/6 - 14:02
 **/
@Data
public class LessonStudentVO {

    private Long stuId;
    private List<Long> classId;
}