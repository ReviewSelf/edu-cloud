package net.edu.module.entity;


import lombok.Data;
import net.edu.framework.mybatis.entity.BaseEntity;

@Data
public class ArchiveSignEntity extends BaseEntity {
    /**
     * 签到表编号
     */
    private Long Id;

    /**
     * 签到记录
     */
    private String record;

    /**
     * 学号
     */
    private String stuId;

    /**
     * 姓名
     */
    private String stuName;

    /**
     * 记分册编号
     */
    private Long bookId;
}
