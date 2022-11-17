package net.edu.module.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProblemSolvingQuery extends Query {
    private Long problemId;
    private Integer problemType;
}
