package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
@TableName("abilityPoint")
public class abilityRelatedEntity {
    /**
     * 自增主键
     */
    @TableId
    private Long id;

    /**
     * 能力图主键
     */
    private Long abilityId;

    /**
     * 知识点代码
     */
    private String code;

    /**
     * 来源
     */
    private Long source;

    /**
     * 来源
     */
    private Long target;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date updateTime;
}
