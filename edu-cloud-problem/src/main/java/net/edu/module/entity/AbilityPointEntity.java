package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
@TableName("ability_kp")
public class AbilityPointEntity {
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
     * 横坐标
     */
    private Integer coordinateX;

    /**
     * 纵坐标
     */
    private Integer coordinateY;

    /**
     * 指标1
     */
    private Integer lv1Num;

    /**
     * 指标2
     */
    private Integer lv2Num;

    /**
     * 指标3
     */
    private Integer lv3Num;

    /**
     * 指标4
     */
    private Integer lv4Num;

    /**
     * 指标5
     */
    private Integer lv5Num;

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
