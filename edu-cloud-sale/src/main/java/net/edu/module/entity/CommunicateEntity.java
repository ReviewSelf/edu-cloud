package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.mybatis.entity.BaseEntity;

import java.util.Date;

/**
 * XinXiHeShi
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-09-05
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("communicate")
public class CommunicateEntity extends BaseEntity {
    private Long id;

    private Long stuId;

    private Long saleId;

    private Date communicateTime;

    private Integer communication;

    private String explanation;
}
