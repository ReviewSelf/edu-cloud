package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;


/**
* 毕业要求查询
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-20
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "毕业要求查询")
public class GraduateRequireQuery extends Query {

    private String title;

    private String  schoolName;

    private String collegeName;

    private String majorName;
}
