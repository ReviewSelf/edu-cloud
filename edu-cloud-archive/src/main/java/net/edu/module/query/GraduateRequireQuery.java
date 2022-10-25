package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;

import java.util.ArrayList;
import java.util.List;


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
    @Schema(description = "标题")
    private String title;

    private String  schoolName;

    private String collegeName;

    private String majorName;

    @Schema(description = "年份")
    private String grade;

    @Schema(description = "机构ID")
    private ArrayList<Integer> orgId;
}
