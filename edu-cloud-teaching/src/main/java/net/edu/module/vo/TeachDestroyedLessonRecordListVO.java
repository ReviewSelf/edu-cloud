package net.edu.module.vo;

import lombok.Data;

import java.util.List;

@Data
public class TeachDestroyedLessonRecordListVO {
    private TeachDestroyedLessonRecordVO vo;

    private List<Long> stuIdList;

}
