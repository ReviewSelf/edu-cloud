package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.mybatis.entity.BaseEntity;

/**
 * @author weng
 * @date 2022/11/3 - 11:37
 **/
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("archive_weight_goal")
public class ArchiveWeightGoalEntity extends BaseEntity {

    /**
     * 编号
     */
    private Long id;

    /**
     * 考核点编号
     */
    private Long assessId;

    /**
     * 支撑度（百分比）
     */
    private Double weight;

    /**
     * 教学目标
     */
    private Long targetId;

    /**
     * 课程编号
     */
    private Long courseId;
}
