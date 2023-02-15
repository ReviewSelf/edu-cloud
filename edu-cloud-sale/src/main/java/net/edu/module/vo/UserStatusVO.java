package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.mybatis.entity.BaseEntity;

import java.util.Date;

/**
 * XinXiHeShi
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-09-05
 */
@Data
@Schema(description = "漏斗转化")
public class UserStatusVO {
    private Long saleId;

    private Integer xiansuo; //线索

    private Integer goutong; //沟通

    private Integer shiting; //试听

    private Integer chengjiao; //成交
}
