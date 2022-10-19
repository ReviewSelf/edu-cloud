package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/12 12:45
 * @Version: 1.0
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("judge_record")
public class JudgeRecordEntity {
    @TableId
    private Long id;
    private Long problemId;
    private Integer problemType;
    private Integer source;
    private Long sourceId;
    @TableField(fill = FieldFill.INSERT)
    private Long userId;
    @TableField(fill = FieldFill.INSERT)
    private Date submitTime;
    private String submitContent;
    private String submitImg;
    private Integer languageType;
    private Integer submitStatus;
    private BigDecimal passRate;
    private BigDecimal memory;
    private Integer judgeType;
    private Integer judgeUser;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer updateTime;

    private BigDecimal score;


}
