package net.edu.module.entity;

import net.edu.framework.mybatis.entity.BaseEntity;

/**
 * @author weng
 * @date 2022/11/18 - 16:30
 **/
public class ArchiveGoalScoreEntity extends BaseEntity {

    /**
     * id
     */
    private Long id;

    /**
     * 学号
     */
    private String stuId;

    /**
     * 教学目标得分
     */
    private String score;

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