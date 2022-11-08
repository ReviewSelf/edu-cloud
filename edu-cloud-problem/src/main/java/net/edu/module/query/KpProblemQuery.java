package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;

/**
 * @Author: 马佳浩
 * @Date: 2022/11/7 10:03
 * @Version: 1.0
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class KpProblemQuery extends Query {
    private String code;
    private Integer difficulty;
    private Integer answered;
    private Long userId;
}
