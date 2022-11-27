package net.edu.module.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.mybatis.entity.BaseEntity;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
@TableName("archive_sign")
public class ArchiveSignEntity extends BaseEntity {

    /**
     * 签到表记录编号
     */
    @TableId
    private Long id;

    /**
     * 签到记录
     */
    private String record;

    /**
     * 删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;



    /**
     * 学号
     */
    private String stuId;

    /**
     * 学生姓名
     */
    private String stuName;

    /**
     * 记分册编号
     */
    private Long bookId;
}
