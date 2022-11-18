package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.mybatis.entity.BaseEntity;

@Data
@EqualsAndHashCode(callSuper=false)
@TableName("problem_solving")
public class ProblemSolvingEntity extends BaseEntity {

    /**
     * 问题id
     */
    private Long problemId;

    /**
     * 问题类型
     */
    private Integer problemType;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

}
