package net.edu.module.entity;

import lombok.Data;
import net.edu.framework.mybatis.entity.BaseEntity;

/**
 * @author weng
 * @date 2022/11/18 - 16:30
 **/
@Data
public class ArchiveGoalScoreEntity extends BaseEntity {

    /**
     * id
     */
    private Long id;

    /**
     * 总结id
     */
    private Long summaryId;

    /**
     * 学号
     */
    private String stuId;

    /**
     * 教学目标得分
     */
    private String score;

    /**
     * 教学目标得分
     */
    private String total;

    /**
     * 学生姓名
     */
    private String stuName;

    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 考核点id
     */
    private Long goalId;



}
