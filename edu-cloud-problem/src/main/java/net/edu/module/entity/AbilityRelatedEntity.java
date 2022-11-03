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
@TableName("ability_kp_related")
public class AbilityRelatedEntity {
    /**
     * 自增主键
     */
    @TableId
    private Long id;

    /**
     * 能力点主键
     */
    private Long pointId;


    /**
     * 能力图主键
     */
    private Long abilityId;

    /**
     * 来源知识点代码
     */
    private String sourceCode;

    /**
     * 来源
     */
    private Long source;

    /**
     * 去向知识点代码
     */
    private String targetCode;

    /**
     * 去向
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
