package net.edu.module.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "沟通表")
public class CommunicateVO extends BaseEntity {
    private Long id;

    private Long stuId;

    private String realName;

    private String username;

    private Long saleId;

    private String mobile;

    private String saleName;

    private Date communicateTime;

    private Integer communication;

    private String explanation;
}
