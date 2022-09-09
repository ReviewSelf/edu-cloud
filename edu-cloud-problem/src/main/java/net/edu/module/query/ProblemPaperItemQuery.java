package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;


import java.util.Date;

/**
* 问题卷关联问题表查询
*
* @author 樊磊 
* @since 1.0.0 2022-09-06
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "问题卷关联问题表查询")
public class ProblemPaperItemQuery extends Query {
    @Schema(description = "paperId")
    private Long paperId;

}