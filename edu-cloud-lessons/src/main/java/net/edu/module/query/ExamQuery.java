package net.edu.module.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;
import net.edu.framework.common.utils.DateUtils;

import java.util.Date;

/**
* 考试查询
*
* @author 小樊 babamu@126.com
* @since 1.0.0 2022-10-09
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "考试查询")
public class ExamQuery extends Query {

    @Schema(description = "监考老师")
    private Long teacherId;

    @Schema(description = "考试名称")
    private String name;

    @Schema(description = "考试地点")
    private String place;

    @Schema(description = "考试说明")
    private String description;

    @Schema(description = "开始时间")
    private String beginTime;

    @Schema(description = "结束时间")

    private String endTime;



}