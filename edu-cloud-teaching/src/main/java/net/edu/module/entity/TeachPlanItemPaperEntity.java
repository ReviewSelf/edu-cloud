package net.edu.module.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
@TableName("teach_plan_item_paper")
public class TeachPlanItemPaperEntity {

    /**
     * id
     */
    private Long id;

    /**
     * 日历id
     */
    private Long itemId;

    /**
     * 试卷id
     */
    private Long paperId;

    /**
     * 试卷名称
     */
    private String paperName;

    /**
     * 试卷类型 0代表预习资料，1代表课堂练习，3代表课后作业
     */
    private Integer paperType;


    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
