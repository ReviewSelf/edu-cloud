package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.mybatis.entity.BaseEntity;

import java.util.Date;

/**
 * @Description: TODO
 * @author: sl
 * @date: 2022年10月08日 19:33
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("lesson_problem")
public class HomeWorkEntity extends BaseEntity {
    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 课堂ID
     */
    private Long lessonId;

    /**
     * 问题ID
     */
    private Long problemId;

    /**
     * 问题名称（冗余，提高效率）
     */
    private String problemName;

    /**
     * 问题类型(选择填空代码)
     */
    private Integer problemType;

    /**
     * 开始时间
     */
    private Date beginTime;

    /**
     * 分数
     */
    private Integer score;

    /**
     * 顺序
     */
    private Integer sort;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 来源
     */
    private Integer source;

    /**
     * 练习类型
     */
    private Integer type;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
