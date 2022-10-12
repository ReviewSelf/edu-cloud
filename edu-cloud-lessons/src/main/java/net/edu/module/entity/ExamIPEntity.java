package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @Author: 马佳浩
 * @Date: 2022/10/12 9:57
 * @Version: 1.0
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("exam_ip")
public class ExamIPEntity {
    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 课堂id
     */
    private Long examId;

    /**
     * IP段
     */
    private String ipRange;

    /**
     * 备注
     */
    private String remark;

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
